package net.v4lproik.googlanime.service.api.myanimelist;

import net.v4lproik.googlanime.service.api.common.ImportOptions;
import net.v4lproik.googlanime.service.api.common.WebsiteAbstract;
import net.v4lproik.googlanime.service.api.myanimelist.models.*;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.*;

import static org.elasticsearch.common.lang3.StringUtils.countMatches;
import static org.elasticsearch.common.lang3.StringUtils.substringBetween;

public class MyAnimeList extends WebsiteAbstract {

    public static final String DOMAIN = "http://myanimelist.net/";
    public static final String USER_AGENT = "iMAL-iOS";
    public static final String[] IGNORE_PROPERTIES = {"sequels", "alternativeVersions", "prequels", "spinoff", "sideStories", "others", "summaries", "adaptations" };
    static Logger log = Logger.getLogger(MyAnimeList.class.getName());
    List<Integer> animeScrapped = new ArrayList<Integer>();
    List<Integer> animeErrorScrapped = new ArrayList<Integer>();

    Set<MyAnimeListEntryDependency> animes = new HashSet<MyAnimeListEntryDependency>();

    MyAnimeListEntry root;

    MyAnimeListEntryFactory entityFactory = new MyAnimeListEntryFactory();
    MyAnimeListEntryDependencyFactory entityFactoryDependency = new MyAnimeListEntryDependencyFactory();

    @Override
    public MyAnimeListAnime crawl(ImportOptions opts) throws IOException {
        MyAnimeListAnime myAnimeListAnime = new MyAnimeListAnime();

        final String type = opts.getType();
        final String name = opts.getQuery();
        final String url = DOMAIN + type + ".php?q=" + name;

        log.debug("Trying to get result from " + url);

        final Connection.Response response = Jsoup.connect(url).userAgent(USER_AGENT).execute();

        //get list or anime ?
        if(response.url().toString().startsWith(DOMAIN + type + "/") && countMatches(response.url().toString(), "/") == 5){
            Document doc = response.parse();
            scrapGeneralInformation(doc, url, type, null);
        }

        return myAnimeListAnime;
    }

    @Override
    public MyAnimeListEntry crawlById(ImportOptions opts) throws IOException {

        final String type = opts.getType();
        final Integer id = opts.getId();
        final Boolean dependency = opts.getDependency();

        if (type == null || id == null)
            throw new IllegalArgumentException("Both type and id argument cannot be null");

        if (type.isEmpty() || id.toString().isEmpty())
            throw new IllegalArgumentException("Both type and id argument cannot be empty");


        root = entityFactory.getEntity(type, id);

        if (!dependency){
            log.debug(String.format("ToScrap %s", id.toString()));

            String url = createURL(id, type);
            Document doc = this.getResultFromJSoup(url, type);

            if (doc == null)
                throw new IOException(String.format("No data fetched for url %s", url));

            scrapGeneralInformation(doc, url, type, root);

            log.debug(root.toString());

            return root;
        }

        letsScrap(root, true);

        return root;
    }

    @Override
    public Set<MyAnimeListEntryDependency> crawlByIdList(ImportOptions opts) throws IOException {

        final String type = opts.getType();
        final Integer id = opts.getId();

        if (type == null || id == null)
            throw new IllegalArgumentException("Both type and id argument cannot be null");

        if (type.isEmpty() || id.toString().isEmpty())
            throw new IllegalArgumentException("Both type and id argument cannot be empty");

        root = entityFactory.getEntity(type, id);
        root.setType(type);

        letsScrap(root, true);

        return animes;
    }

    MyAnimeListEntry letsScrap(MyAnimeListEntry toScrap, Boolean first) throws IOException {

        String url;
        Document doc;

        while (toScrap != null){

            String type = toScrap.getType();
            Integer id = toScrap.getId();

            if (type == null || id == null)
                throw new IllegalArgumentException("Both type and id argument cannot be null");

            if (type.isEmpty() || id.toString().isEmpty())
                throw new IllegalArgumentException("Both type and id argument cannot be empty");

            if (!animeScrapped.contains(toScrap.getId())) {
                log.debug(String.format("ToScrap %s => %s", id.toString(), toScrap.getTitle()));

                url = createURL(id, type);
                doc = getResultFromJSoup(url, type);

                if (doc == null) {
                    animeErrorScrapped.add(id);
                } else {
                    scrapGeneralInformation(doc, url, type, toScrap);
                    animeScrapped.add(id);

                    MyAnimeListEntryDependency myAnimeListEntryDependency = convertIntoDependencyObject(toScrap);

                    animes.add(myAnimeListEntryDependency);
                }

                log.debug(toScrap.toString());
            }

            MyAnimeListEntry nextToScrap = whoSNext(toScrap);

            // Tricky part
            if (nextToScrap == null){
                letsScrap(toScrap.getParent(), false);
            }

            toScrap = nextToScrap;
        }

        return root;
    }

    public Document getResultFromJSoup(String url, String type) throws IOException {
        log.debug("Trying to get result from " + url);

        try {
            //delay between 0 and 5s
            long delay = (0 + new Random().nextInt(5)) * 1000;
            Integer seconds = (int) (delay / 1000) % 60 ;
            log.debug(String.format("Delay : %ss", seconds.toString()));
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Connection.Response response = Jsoup.connect(url).userAgent(USER_AGENT).execute();

        Document doc = null;

        if(response.url().toString().startsWith(DOMAIN + type + "/") && countMatches(response.url().toString(), "/") == 5){
            doc = response.parse();
        }

        log.debug(String.format("Response url %s", response.url()));

        return doc;
    }

    MyAnimeListEntry whoSNext(MyAnimeListEntry lastScrapped){

        log.debug(String.format("Animes that have been scrapped %s ", Arrays.asList(animeScrapped)));
        log.debug(String.format("Animes that have been errorscrapped %s ", Arrays.asList(animeErrorScrapped)));


        for (MyAnimeListEntry entry : lastScrapped.getAdaptations()){
            Integer id = entry.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return entry;
            }
        }

        for (MyAnimeListEntry entry : lastScrapped.getAlternativeVersions()){
            Integer id = entry.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return entry;
            }
        }

        for (MyAnimeListEntry entry : lastScrapped.getPrequels()){
            Integer id = entry.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return entry;
            }
        }

        for (MyAnimeListEntry entry : lastScrapped.getSequels()){
            int id = entry.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return entry;
            }
        }

        for (MyAnimeListEntry entry : lastScrapped.getSideStories()){
            int id = entry.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return entry;
            }
        }

        for (MyAnimeListEntry entry : lastScrapped.getOthers()){
            int id = entry.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return entry;
            }
        }

        for (MyAnimeListEntry entry : lastScrapped.getSummaries()){
            int id = entry.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return entry;
            }
        }

        return null;
    }

    MyAnimeListEntryDependency convertIntoDependencyObject(MyAnimeListEntry from){

        MyAnimeListEntryDependency myAnimeListEntryDependency = entityFactoryDependency.getEntity(from.getType(), from.getId());
        BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);
        MyAnimeListEntryDependencyId myAnimeListEntryDependencyId = new MyAnimeListEntryDependencyId();
        Integer id;
        String title;
        String type;

        for (MyAnimeListEntry entry : from.getAdaptations()){
            id = entry.getId();
            title = entry.getEnglishTitle();
            type = entry.getType();
            myAnimeListEntryDependency = entityFactoryDependency.getEntity(type, id);
            BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);

            myAnimeListEntryDependencyId.setId(id);
            myAnimeListEntryDependencyId.setEnglishTitle(title);
            myAnimeListEntryDependencyId.setType(type);

            myAnimeListEntryDependency.getAdaptations().add(myAnimeListEntryDependencyId);

        }

        for (MyAnimeListEntry entry : from.getAlternativeVersions()){
            id = entry.getId();
            title = entry.getEnglishTitle();
            type = entry.getType();
            myAnimeListEntryDependency = entityFactoryDependency.getEntity(type, id);
            BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);

            myAnimeListEntryDependencyId.setId(id);
            myAnimeListEntryDependencyId.setEnglishTitle(title);
            myAnimeListEntryDependencyId.setType(type);

            myAnimeListEntryDependency.getAlternativeVersions().add(myAnimeListEntryDependencyId);
        }

        for (MyAnimeListEntry entry : from.getPrequels()){
            id = entry.getId();
            title = entry.getEnglishTitle();
            type = entry.getType();
            myAnimeListEntryDependency = entityFactoryDependency.getEntity(type, id);
            BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);

            myAnimeListEntryDependencyId.setId(id);
            myAnimeListEntryDependencyId.setEnglishTitle(title);
            myAnimeListEntryDependencyId.setType(type);

            myAnimeListEntryDependency.getPrequels().add(myAnimeListEntryDependencyId);
        }

        for (MyAnimeListEntry entry : from.getSequels()){
            id = entry.getId();
            title = entry.getEnglishTitle();
            type = entry.getType();
            myAnimeListEntryDependency = entityFactoryDependency.getEntity(type, id);
            BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);

            myAnimeListEntryDependencyId.setId(id);
            myAnimeListEntryDependencyId.setEnglishTitle(title);
            myAnimeListEntryDependencyId.setType(type);

            myAnimeListEntryDependency.getSequels().add(myAnimeListEntryDependencyId);
        }

        for (MyAnimeListEntry entry : from.getSideStories()){
            id = entry.getId();
            title = entry.getEnglishTitle();
            type = entry.getType();
            myAnimeListEntryDependency = entityFactoryDependency.getEntity(type, id);
            BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);

            myAnimeListEntryDependencyId.setId(id);
            myAnimeListEntryDependencyId.setEnglishTitle(title);
            myAnimeListEntryDependencyId.setType(type);

            myAnimeListEntryDependency.getSideStories().add(myAnimeListEntryDependencyId);
        }

        for (MyAnimeListEntry entry : from.getOthers()) {
            id = entry.getId();
            title = entry.getEnglishTitle();
            type = entry.getType();
            myAnimeListEntryDependency = entityFactoryDependency.getEntity(type, id);
            BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);

            myAnimeListEntryDependencyId.setId(id);
            myAnimeListEntryDependencyId.setEnglishTitle(title);
            myAnimeListEntryDependencyId.setType(type);

            myAnimeListEntryDependency.getOthers().add(myAnimeListEntryDependencyId);
        }

        for (MyAnimeListEntry entry : from.getSummaries()) {
            id = entry.getId();
            title = entry.getEnglishTitle();
            type = entry.getType();
            myAnimeListEntryDependency = entityFactoryDependency.getEntity(type, id);
            BeanUtils.copyProperties(from, myAnimeListEntryDependency, IGNORE_PROPERTIES);

            myAnimeListEntryDependencyId.setId(id);
            myAnimeListEntryDependencyId.setEnglishTitle(title);
            myAnimeListEntryDependencyId.setType(type);

            myAnimeListEntryDependency.getSummaries().add(myAnimeListEntryDependencyId);
        }

        return myAnimeListEntryDependency;
    }

    Integer getIdFromLink(String link) {
        try {
            if (link.startsWith("http") || link.startsWith("https"))
                return Integer.parseInt(link.split("/")[4].split("/")[0]);
            else
                return Integer.parseInt(link.split("/")[2].split("/")[0]);
        } catch (Exception e){
            log.debug(String.format("Error parsing id from link : %s", link));
        }

        return null;
    }

    String getTypeFromLink(String link) {
        try {
            if (link.startsWith("http") || link.startsWith("https"))
                return link.split("/")[3].split("/")[0];
            else
                return link.split("/")[1].split("/")[0];
        } catch (Exception e){
            log.debug(String.format("Error parsing type from link : %s", link));
        }

        return null;
    }

    public String createURL(Integer id, String type){
        return DOMAIN + type.toString() + "/" + id.toString() + "/";
    }

    MyAnimeListEntry scrapGeneralInformation(Document doc, String url, String type, MyAnimeListEntry myAnimeListEntry){
        String pattern;

        Integer id;

        id = this.getIdFromLink(url);

        if (myAnimeListEntry == null){
            myAnimeListEntry = entityFactory.getEntity(type, id);
        }

        //get type
        myAnimeListEntry.setType(type);

        //get main title
        myAnimeListEntry.setTitle(doc.select("h1").first().ownText());

        //get image
        myAnimeListEntry.setPosterImage(doc.select("meta[property=og:image]").attr("content"));

        //parse for general information - work in porgress
        Elements tds = doc.select("td");
        for (Element td : tds) {
            if (td.text().startsWith("EditSynopsis")) {
                myAnimeListEntry.setSynopsis(td.text().substring(12, td.text().length()));
            }else {
                if (td.text().startsWith("EditRelated")) {
                    String tmp[] = substringBetween(td.html(), "Related " + type.substring(0,1).toUpperCase() + type.substring(1) + "</h2>", "<h2>").split("<br>");

                    for (String line : tmp){
                        if (line.startsWith("Sequel:")){
                            log.debug("Sequel has been found");

                            Document docTmp = Jsoup.parse(line);
                            Element link = docTmp.select("a").first();
                            String linkHref = link.attr("href");
                            String title =  link.text();
                            id = this.getIdFromLink(linkHref);
                            type = this.getTypeFromLink(linkHref);

                            if (id != null){
                                List<MyAnimeListEntry> sequels = new ArrayList<>();
                                MyAnimeListEntry sequel = entityFactory.getEntity(type, id);
                                sequel.setId(id);
                                sequel.setType(type);
                                sequel.setTitle(title);
                                sequel.setParent(myAnimeListEntry);

                                //get sequels
                                sequels = myAnimeListEntry.getSequels();
                                sequels.add(sequel);
                            }
                        }else {
                            if (line.startsWith("Side story:")) {
                                log.debug("Side Stories have been found");

                                Document docTmp = Jsoup.parse(line);
                                Elements links = docTmp.select("a");

                                for (Element link : links) {
                                    String linkHref = link.attr("href");
                                    String title =  link.text();
                                    id = this.getIdFromLink(linkHref);
                                    type = this.getTypeFromLink(linkHref);

                                    if (id != null) {
                                        List<MyAnimeListEntry> sideStories = new ArrayList<>();
                                        MyAnimeListEntry sideStory = entityFactory.getEntity(type, id);
                                        sideStory.setId(id);
                                        sideStory.setType(type);
                                        sideStory.setTitle(title);
                                        sideStory.setParent(myAnimeListEntry);


                                        //get sequels
                                        sideStories = myAnimeListEntry.getSideStories();
                                        sideStories.add(sideStory);
                                    }
                                }
                            }else{
                                if (line.startsWith("Spin-off:")) {
                                    log.debug("Spin Off have been found");

                                    Document docTmp = Jsoup.parse(line);
                                    Elements links = docTmp.select("a");

                                    for (Element link : links) {
                                        String linkHref = link.attr("href");
                                        String title =  link.text();
                                        id = this.getIdFromLink(linkHref);
                                        type = this.getTypeFromLink(linkHref);

                                        if (id != null) {
                                            List<MyAnimeListEntry> spinOffs = new ArrayList<>();
                                            MyAnimeListEntry spinOff = entityFactory.getEntity(type, id);
                                            spinOff.setId(id);
                                            spinOff.setType(type);
                                            spinOff.setTitle(title);
                                            spinOff.setParent(myAnimeListEntry);

                                            //get sequels
                                            spinOffs = myAnimeListEntry.getSpinoff();
                                            spinOffs.add(spinOff);
                                        }
                                    }
                                }else{
                                    if (line.startsWith("Other:")) {
                                        log.debug("Others have been found");

                                        Document docTmp = Jsoup.parse(line);
                                        Elements links = docTmp.select("a");

                                        for (Element link : links) {
                                            String linkHref = link.attr("href");
                                            String title =  link.text();
                                            id = this.getIdFromLink(linkHref);
                                            type = this.getTypeFromLink(linkHref);

                                            if (id != null) {
                                                List<MyAnimeListEntry> others = new ArrayList<>();
                                                MyAnimeListEntry other = entityFactory.getEntity(type, id);
                                                other.setId(id);
                                                other.setType(type);
                                                other.setTitle(title);
                                                other.setParent(myAnimeListEntry);


                                                //get sequels
                                                others = myAnimeListEntry.getOthers();
                                                others.add(other);
                                            }
                                        }
                                    }else{
                                        if (line.startsWith("Prequel:")) {
                                            log.debug("Prequels have been found");

                                            Document docTmp = Jsoup.parse(line);
                                            Elements links = docTmp.select("a");

                                            for (Element link : links) {
                                                String linkHref = link.attr("href");
                                                String title =  link.text();
                                                id = this.getIdFromLink(linkHref);
                                                type = this.getTypeFromLink(linkHref);

                                                if (id != null) {
                                                    List<MyAnimeListEntry> prequels = new ArrayList<>();
                                                    MyAnimeListEntry prequel = entityFactory.getEntity(type, id);
                                                    prequel.setId(id);
                                                    prequel.setType(type);
                                                    prequel.setTitle(title);
                                                    prequel.setParent(myAnimeListEntry);

                                                    //get sequels
                                                    prequels = myAnimeListEntry.getPrequels();
                                                    prequels.add(prequel);
                                                }
                                            }
                                        }else{
                                            if (line.startsWith("Alternative version:")) {
                                                log.debug("Alternative versions have been found");

                                                Document docTmp = Jsoup.parse(line);
                                                Elements links = docTmp.select("a");

                                                for (Element link : links) {
                                                    String linkHref = link.attr("href");
                                                    String title =  link.text();
                                                    id = this.getIdFromLink(linkHref);
                                                    type = this.getTypeFromLink(linkHref);

                                                    if (id != null) {
                                                        List<MyAnimeListEntry> alternativeVersions = new ArrayList<>();
                                                        MyAnimeListEntry alternativeVersion = entityFactory.getEntity(type, id);
                                                        alternativeVersion.setId(id);
                                                        alternativeVersion.setType(type);
                                                        alternativeVersion.setTitle(title);
                                                        alternativeVersion.setParent(myAnimeListEntry);

                                                        //get sequels
                                                        alternativeVersions = myAnimeListEntry.getOthers();
                                                        alternativeVersions.add(alternativeVersion);
                                                    }
                                                }
                                            }else {
                                                if (line.startsWith("Adaptation")) {
                                                    log.debug("Adaptations have been found");

                                                    Document docTmp = Jsoup.parse(line);
                                                    Elements links = docTmp.select("a");

                                                    for (Element link : links) {
                                                        String linkHref = link.attr("href");
                                                        String title =  link.text();
                                                        id = this.getIdFromLink(linkHref);
                                                        type = this.getTypeFromLink(linkHref);

                                                        if (id != null) {
                                                            List<MyAnimeListEntry> adaptations = new ArrayList<>();
                                                            MyAnimeListEntry adaptation = entityFactory.getEntity(type, id);
                                                            adaptation.setId(id);
                                                            adaptation.setType(type);
                                                            adaptation.setTitle(title);
                                                            adaptation.setParent(myAnimeListEntry);

                                                            //get sequels
                                                            adaptations = myAnimeListEntry.getAdaptations();
                                                            adaptations.add(adaptation);
                                                        }
                                                    }
                                                }else {
                                                    if (line.startsWith("Summary")) {
                                                        log.debug("Summaries have been found");

                                                        Document docTmp = Jsoup.parse(line);
                                                        Elements links = docTmp.select("a");

                                                        for (Element link : links) {
                                                            String linkHref = link.attr("href");
                                                            String title =  link.text();
                                                            id = this.getIdFromLink(linkHref);
                                                            type = this.getTypeFromLink(linkHref);

                                                            if (id != null) {
                                                                List<MyAnimeListEntry> summaries = new ArrayList<>();
                                                                MyAnimeListEntry summary = entityFactory.getEntity(type, id);
                                                                summary.setId(id);
                                                                summary.setType(type);
                                                                summary.setTitle(title);
                                                                summary.setParent(myAnimeListEntry);

                                                                //get sequels
                                                                summaries = myAnimeListEntry.getSummaries();
                                                                summaries.add(summary);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Elements divs = doc.select("div");

        for (Element div : divs) {

            if (div.text().startsWith("Synopsis: "))
                myAnimeListEntry.setSynopsis(div.text().substring(9, div.text().length()));

            if (div.text().startsWith("Synonyms: "))
                myAnimeListEntry.setSynonyms(div.text().substring(10, div.text().length()).replace(", ", ",").split(","));

            if (div.text().startsWith("English: "))
                myAnimeListEntry.setEnglishTitle(div.text().substring(9, div.text().length()));

            if (div.text().startsWith("Japanese: "))
                myAnimeListEntry.setJapaneseTitle(div.text().substring(10, div.text().length()));

            if (div.text().startsWith("Episodes: ")){
                MyAnimeListAnime anime = (MyAnimeListAnime) myAnimeListEntry;
                anime.setEpisodeCount(div.text().substring(10, div.text().length()));
            }

            pattern = type.equals("manga") ? "Published: " : "Aired: ";

            if (div.text().startsWith(pattern)) {
                String[] tmp;
                tmp = div.text().substring(7, div.text().length()).split("to ");
                try {
                    myAnimeListEntry.setStartedAiringDate(tmp[0]);
                    myAnimeListEntry.setFinishedAiringDate(tmp[1]);
                }catch(Exception e){
                    log.debug("Error parsing airing/finishing dates");
                }
            }

            if (div.text().startsWith("Producers: ")){
                MyAnimeListAnime anime = (MyAnimeListAnime) myAnimeListEntry;
                anime.setProducers(div.text().substring(11, div.text().length()).replace(", ", ",").split(","));
            }


            if (div.text().startsWith("Genres: "))
                myAnimeListEntry.setGenres(div.text().substring(8, div.text().length()).replace(", ", ",").split(","));

            if (div.text().startsWith("Authors: ")){

                String parts[] = div.text().substring(9, div.text().length()).replace(", ", ",").split(",");
                ArrayList<String> listItems = new ArrayList<String>();

                List<MyAnimeListAuthor> authors = myAnimeListEntry.getAuthors();
                for (int i = 0; i < parts.length; i = i+2) {
                    MyAnimeListAuthor author = new MyAnimeListAuthor();
                    try{
                        listItems.add(parts[i] + ", " + parts[i+1]);
                        author.setFirstName(parts[i]);
                        author.setLastName(parts[i + 1]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        author.setFirstName(parts[i]);
                    }
                    log.info(String.format("Add new author %s", author.toString()));
                }
            }

            if (div.text().startsWith("Duration: ")) {
                MyAnimeListAnime anime = (MyAnimeListAnime) myAnimeListEntry;
                anime.setEpisodeLength(div.text().substring(10, div.text().length()));
            }

            if (div.text().startsWith("Rating: "))
                myAnimeListEntry.setAgeRating(div.text().substring(8, div.text().length()));

            if (div.text().startsWith("Score: "))
                myAnimeListEntry.setScore(div.text().substring(7, div.text().length()));

            if (div.text().startsWith("Ranked: "))
                myAnimeListEntry.setRank(div.text().substring(8, div.text().length()));

            if (div.text().startsWith("Popularity: "))
                myAnimeListEntry.setPopularity(div.text().substring(12, div.text().length()));

        }

        pattern = type.equals("manga") ? "Add character | More charactersCharacters" : "Add character | More charactersCharacters & Voice Actors";

        Elements h2s = doc.select("h2");
        for (Element h2 : h2s) {
            if (h2.text().equals("Popular Tags")){
                log.debug("Popular tags have been found");
                Elements els = h2.nextElementSibling().select("span").select("a");

                String[] tags = new String[els.size()];
                int i = 0;
                for (Element tag : els){
                    tags[i] = tag.text();
                    i++;
                }
                myAnimeListEntry.setTags(tags);
            }else{
                if (h2.text().equals(pattern)) {
                    log.debug("Characters have been found");

                    Node current = h2.nextSibling();

                    while (current.outerHtml().startsWith("<table")){
                        Element el = (Element) current;

                        // Get character info
                        MyAnimeListCharacter character = new MyAnimeListCharacter();
                        try{
                            String characterFullName = el.select("td").get(1).select("a").text();
                            String role = el.select("td").get(1).select("small").text();

                            String[] parts = characterFullName.split(",");

                            if (parts.length == 2){
                                character.setFirstName(parts[1]);
                                character.setLastName(parts[0]);
                            }else {
                                if (parts.length == 1)
                                    character.setLastName(characterFullName);
                            }
                            character.setRole(role);
                            myAnimeListEntry.getCharacters().add(character);

                            log.info(String.format("Add new character %s", character.toString()));
                        }catch (Exception e){
                            log.debug("Error when trying to get character's name");
                        }

                        current = current.nextSibling();
                    }
                }else {
                    //Add authors for anime
                    if (h2.text().equals("Add staff | More staffStaff")) {
                        log.debug("Authors have been found");

                        Node current = h2.nextElementSibling();
                        Element el = (Element) current;

                        Elements trs = el.select("tr");

                        for (Element tr : trs){

                            // Get character info
                            MyAnimeListAuthor author = new MyAnimeListAuthor();
                            try{
                                String characterFullName = tr.select("td").get(1).select("a").text();
                                String[] jobs = tr.select("td").get(1).select("small").text().split(" ,");

                                String[] parts = characterFullName.split(",");

                                if (parts.length == 2){
                                    author.setFirstName(parts[0]);
                                    author.setLastName(parts[1]);
                                }else {
                                    if (parts.length == 1)
                                        author.setLastName(characterFullName);
                                }
                                author.setJob(jobs);
                                myAnimeListEntry.getAuthors().add(author);

                                log.info(String.format("Add new author %s", author.toString()));
                            }catch (Exception e){
                                log.debug("Error when trying to get author's name");
                            }
                        }

                    }
                }
            }
        }

        return myAnimeListEntry;
    }

    @Override
    public void call() {
        throw new NotImplementedException();
    }
}
