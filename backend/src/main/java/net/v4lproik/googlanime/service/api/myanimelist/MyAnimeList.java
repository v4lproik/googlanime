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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.elasticsearch.common.lang3.StringUtils.countMatches;
import static org.elasticsearch.common.lang3.StringUtils.substringBetween;

/**
 * Created by joel on 08/05/2015.
 */
public class MyAnimeList extends WebsiteAbstract {

    public static final String DOMAIN = "http://myanimelist.net/";
    public static final String API = "";
    public static final String USER_AGENT = "iMAL-iOS";
    public static final String[] URL_GRAB = new String[]{"characters", "characters#staff"};
    public static final String[] IGNORE_PROPERTIES = {"sequels", "alternativeVersions", "prequels", "spinoff", "sideStories", "others", "summaries", "adaptations" };
    static Logger log = Logger.getLogger(MyAnimeList.class.getName());
    private List<Integer> animeScrapped = new ArrayList<Integer>();
    private List<Integer> animeErrorScrapped = new ArrayList<Integer>();

    private List<MyAnimeListAnimeDependency> animes = new ArrayList<>();

    private MyAnimeListAnime root;

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
    public MyAnimeListAnime crawlById(ImportOptions opts) throws IOException {

        final String type = opts.getType();
        final Integer id = opts.getId();
        final Boolean dependency = opts.getDependency();

        if (type == null || id == null)
            throw new IllegalArgumentException("Both type and id argument cannot be null");

        if (type.isEmpty() || id.toString().isEmpty())
            throw new IllegalArgumentException("Both type and id argument cannot be empty");

        // Set the root here so it gets never overwritten
        root = new MyAnimeListAnime(id);
        root.setType(type);

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
    public List<MyAnimeListAnimeDependency> crawlByIdList(ImportOptions opts) throws IOException {

        final String type = opts.getType();
        final Integer id = opts.getId();

        if (type == null || id == null)
            throw new IllegalArgumentException("Both type and id argument cannot be null");

        if (type.isEmpty() || id.toString().isEmpty())
            throw new IllegalArgumentException("Both type and id argument cannot be empty");

        // Set the root here so it gets never overwritten
        root = new MyAnimeListAnime(id);
        root.setType(type);

        letsScrap(root, true);

        return animes;
    }

    protected MyAnimeListAnime letsScrap(MyAnimeListAnime toScrap, Boolean first) throws IOException {

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

                    MyAnimeListAnimeDependency myAnimeListAnimeDependency = convertIntoDependencyObject(toScrap);

                    animes.add(myAnimeListAnimeDependency);
                }

                log.debug(toScrap.toString());
            }

            MyAnimeListAnime nextToScrap = whoSNext(toScrap);

            // Tricky part
            if (nextToScrap == null){
                letsScrap(toScrap.getParent(), false);
            }

            toScrap = nextToScrap;
        }

        return root;
    }

    protected Document getResultFromJSoup(String url, String type) throws IOException {
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

    protected MyAnimeListAnime whoSNext(MyAnimeListAnime lastScrapped){

        log.debug(String.format("Animes that have been scrapped %s ", Arrays.asList(animeScrapped)));
        log.debug(String.format("Animes that have been errorscrapped %s ", Arrays.asList(animeErrorScrapped)));


        for (Object anime : lastScrapped.getAdaptations()){
            MyAnimeListAnime animeSelected = (MyAnimeListAnime) anime;
            Integer id = animeSelected.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return animeSelected;
            }
        }

        for (Object anime : lastScrapped.getAlternativeVersions()){
            MyAnimeListAnime animeSelected = (MyAnimeListAnime) anime;
            int id = animeSelected.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return animeSelected;
            }
        }

        for (Object anime : lastScrapped.getPrequels()){
            MyAnimeListAnime animeSelected = (MyAnimeListAnime) anime;
            int id = animeSelected.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return animeSelected;
            }
        }

        for (Object anime : lastScrapped.getSequels()){
            MyAnimeListAnime animeSelected = (MyAnimeListAnime) anime;
            int id = animeSelected.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return animeSelected;
            }
        }

        for (Object anime : lastScrapped.getSideStories()){
            MyAnimeListAnime animeSelected = (MyAnimeListAnime) anime;
            int id = animeSelected.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return animeSelected;
            }
        }

        for (Object anime : lastScrapped.getOthers()){
            MyAnimeListAnime animeSelected = (MyAnimeListAnime) anime;
            int id = animeSelected.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return animeSelected;
            }
        }

        for (Object anime : lastScrapped.getSummaries()){
            MyAnimeListAnime animeSelected = (MyAnimeListAnime) anime;
            int id = animeSelected.getId();

            if (!animeScrapped.contains(id) && !animeErrorScrapped.contains(id)){
                return animeSelected;
            }
        }

        return null;
    }

    protected MyAnimeListAnimeDependency convertIntoDependencyObject(MyAnimeListAnime from){

        MyAnimeListAnimeDependency myAnimeListAnimeDependency = new MyAnimeListAnimeDependency();
        MyAnimeListAnimeDependencyId myAnimeListAnimeDependencyId = new MyAnimeListAnimeDependencyId();
        Integer id;
        String title;
        String type;

        //Copy value into MyAnimeListDependency
        BeanUtils.copyProperties(from, myAnimeListAnimeDependency, IGNORE_PROPERTIES);

        for (MyAnimeListAnime anime : from.getAdaptations()){
            id = anime.getId();
            title = anime.getEnglishTitle();
            type = anime.getType();

            myAnimeListAnimeDependencyId.setId(id);
            myAnimeListAnimeDependencyId.setEnglishTitle(title);
            myAnimeListAnimeDependencyId.setType(type);

            myAnimeListAnimeDependency.getAdaptations().add(myAnimeListAnimeDependencyId);

        }

        for (MyAnimeListAnime anime : from.getAlternativeVersions()){
            id = anime.getId();
            title = anime.getEnglishTitle();
            type = anime.getType();

            myAnimeListAnimeDependencyId.setId(id);
            myAnimeListAnimeDependencyId.setEnglishTitle(title);
            myAnimeListAnimeDependencyId.setType(type);

            myAnimeListAnimeDependency.getAlternativeVersions().add(myAnimeListAnimeDependencyId);
        }

        for (MyAnimeListAnime anime : from.getPrequels()){
            id = anime.getId();
            title = anime.getEnglishTitle();
            type = anime.getType();

            myAnimeListAnimeDependencyId.setId(id);
            myAnimeListAnimeDependencyId.setEnglishTitle(title);
            myAnimeListAnimeDependencyId.setType(type);

            myAnimeListAnimeDependency.getPrequels().add(myAnimeListAnimeDependencyId);
        }

        for (MyAnimeListAnime anime : from.getSequels()){
            id = anime.getId();
            title = anime.getEnglishTitle();
            type = anime.getType();

            myAnimeListAnimeDependencyId.setId(id);
            myAnimeListAnimeDependencyId.setEnglishTitle(title);
            myAnimeListAnimeDependencyId.setType(type);

            myAnimeListAnimeDependency.getSequels().add(myAnimeListAnimeDependencyId);
        }

        for (MyAnimeListAnime anime : from.getSideStories()){
            id = anime.getId();
            title = anime.getEnglishTitle();
            type = anime.getType();

            myAnimeListAnimeDependencyId.setId(id);
            myAnimeListAnimeDependencyId.setEnglishTitle(title);
            myAnimeListAnimeDependencyId.setType(type);

            myAnimeListAnimeDependency.getSideStories().add(myAnimeListAnimeDependencyId);
        }

        for (MyAnimeListAnime anime : from.getOthers()) {
            id = anime.getId();
            title = anime.getEnglishTitle();
            type = anime.getType();

            myAnimeListAnimeDependencyId.setId(id);
            myAnimeListAnimeDependencyId.setEnglishTitle(title);
            myAnimeListAnimeDependencyId.setType(type);

            myAnimeListAnimeDependency.getOthers().add(myAnimeListAnimeDependencyId);
        }

        for (MyAnimeListAnime anime : from.getSummaries()) {
            id = anime.getId();
            title = anime.getEnglishTitle();
            type = anime.getType();

            myAnimeListAnimeDependencyId.setId(id);
            myAnimeListAnimeDependencyId.setEnglishTitle(title);
            myAnimeListAnimeDependencyId.setType(type);

            myAnimeListAnimeDependency.getSummaries().add(myAnimeListAnimeDependencyId);
        }

        return myAnimeListAnimeDependency;
    }

    protected Integer getIdFromLink(String link) {
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

    protected String getTypeFromLink(String link) {
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

    protected String createURL(Integer id, String type){
        return DOMAIN + type.toString() + "/" + id.toString() + "/";
    }

    private MyAnimeListAnime scrapGeneralInformation(Document doc, String url, String type, MyAnimeListAnime myAnimeListAnime){
        String pattern;

        if (myAnimeListAnime == null) myAnimeListAnime = new MyAnimeListAnime();

        //get id
        myAnimeListAnime.setId(this.getIdFromLink(url));

        //get type
        myAnimeListAnime.setType(type);

        //get main title
        myAnimeListAnime.setTitle(doc.select("h1").first().ownText());

        //get image
        myAnimeListAnime.setPosterImage(doc.select("meta[property=og:image]").attr("content"));

        //parse for general information - work in porgress
        Elements tds = doc.select("td");
        for (Element td : tds) {
            if (td.text().startsWith("EditSynopsis")) {
                myAnimeListAnime.setSynopsis(td.text().substring(12, td.text().length()));
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
                            Integer id = this.getIdFromLink(linkHref);
                            type = this.getTypeFromLink(linkHref);

                            if (id != null){
                                MyAnimeListAnime sequel = new MyAnimeListAnime();
                                sequel.setId(id);
                                sequel.setType(type);
                                sequel.setTitle(title);
                                sequel.setParent(myAnimeListAnime);

                                //get sequels
                                List<MyAnimeListAnime> sequels = myAnimeListAnime.getSequels();
                                sequels.add(sequel);
                            }
                        }else {
                            if (line.startsWith("Side story:")) {
                                log.debug("Side Stories have been found");

                                Document docTmp = Jsoup.parse(line);
                                Elements links = docTmp.select("a");

                                for (Element link : links) {
                                    String linkHref = link.attr("href");
                                    String title = link.text();
                                    Integer id = this.getIdFromLink(linkHref);
                                    type = this.getTypeFromLink(linkHref);

                                    if (id != null) {
                                        MyAnimeListAnime sideStory = new MyAnimeListAnime();
                                        sideStory.setId(id);
                                        sideStory.setType(type);
                                        sideStory.setTitle(title);
                                        sideStory.setParent(myAnimeListAnime);


                                        //get sequels
                                        List<MyAnimeListAnime> sideStories = myAnimeListAnime.getSideStories();
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
                                        String title = link.text();
                                        Integer id = this.getIdFromLink(linkHref);
                                        type = this.getTypeFromLink(linkHref);

                                        if (id != null) {
                                            MyAnimeListAnime spinOff = new MyAnimeListAnime();
                                            spinOff.setId(id);
                                            spinOff.setType(type);
                                            spinOff.setTitle(title);
                                            spinOff.setParent(myAnimeListAnime);

                                            //get sequels
                                            List<MyAnimeListAnime> spinOffs = myAnimeListAnime.getSpinoff();
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
                                            String title = link.text();
                                            Integer id = this.getIdFromLink(linkHref);
                                            type = this.getTypeFromLink(linkHref);

                                            if (id != null) {
                                                MyAnimeListAnime other = new MyAnimeListAnime();
                                                other.setId(id);
                                                other.setType(type);
                                                other.setTitle(title);
                                                other.setParent(myAnimeListAnime);


                                                //get sequels
                                                List<MyAnimeListAnime> others = myAnimeListAnime.getOthers();
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
                                                String title = link.text();
                                                Integer id = this.getIdFromLink(linkHref);
                                                type = this.getTypeFromLink(linkHref);

                                                if (id != null) {
                                                    MyAnimeListAnime prequel = new MyAnimeListAnime();
                                                    prequel.setId(id);
                                                    prequel.setType(type);
                                                    prequel.setTitle(title);
                                                    prequel.setParent(myAnimeListAnime);

                                                    //get sequels
                                                    List<MyAnimeListAnime> prequels = myAnimeListAnime.getPrequels();
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
                                                    String title = link.text();
                                                    Integer id = this.getIdFromLink(linkHref);
                                                    type = this.getTypeFromLink(linkHref);

                                                    if (id != null) {
                                                        MyAnimeListAnime alternativeVersion = new MyAnimeListAnime();
                                                        alternativeVersion.setId(id);
                                                        alternativeVersion.setType(type);
                                                        alternativeVersion.setTitle(title);
                                                        alternativeVersion.setParent(myAnimeListAnime);

                                                        //get sequels
                                                        List<MyAnimeListAnime> alternativeVersions = myAnimeListAnime.getOthers();
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
                                                        String title = link.text();
                                                        Integer id = this.getIdFromLink(linkHref);
                                                        type = this.getTypeFromLink(linkHref);

                                                        if (id != null) {
                                                            MyAnimeListAnime adaptations = new MyAnimeListAnime();
                                                            adaptations.setId(id);
                                                            adaptations.setType(type);
                                                            adaptations.setTitle(title);
                                                            adaptations.setParent(myAnimeListAnime);

                                                            //get sequels
                                                            List<MyAnimeListAnime> alternativeVersions = myAnimeListAnime.getAdaptations();
                                                            alternativeVersions.add(adaptations);
                                                        }
                                                    }
                                                }else {
                                                    if (line.startsWith("Summary")) {
                                                        log.debug("Summaries have been found");

                                                        Document docTmp = Jsoup.parse(line);
                                                        Elements links = docTmp.select("a");

                                                        for (Element link : links) {
                                                            String linkHref = link.attr("href");
                                                            String title = link.text();
                                                            Integer id = this.getIdFromLink(linkHref);
                                                            type = this.getTypeFromLink(linkHref);

                                                            if (id != null) {
                                                                MyAnimeListAnime summary = new MyAnimeListAnime();
                                                                summary.setId(id);
                                                                summary.setType(type);
                                                                summary.setTitle(title);
                                                                summary.setParent(myAnimeListAnime);

                                                                //get sequels
                                                                List<MyAnimeListAnime> summaries = myAnimeListAnime.getSummaries();
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
                myAnimeListAnime.setSynopsis(div.text().substring(9, div.text().length()));

            if (div.text().startsWith("Synonyms: "))
                myAnimeListAnime.setSynonyms(div.text().substring(10, div.text().length()).replace(", ", ",").split(","));

            if (div.text().startsWith("English: "))
                myAnimeListAnime.setEnglishTitle(div.text().substring(9, div.text().length()));

            if (div.text().startsWith("Japanese: "))
                myAnimeListAnime.setJapaneseTitle(div.text().substring(10, div.text().length()));

            if (div.text().startsWith("Episodes: "))
                myAnimeListAnime.setEpisodeCount(div.text().substring(10, div.text().length()));

            pattern = type.equals("manga") ? "Published: " : "Aired: ";

            if (div.text().startsWith(pattern)) {
                String[] tmp;
                tmp = div.text().substring(7, div.text().length()).split("to ");
                try {
                    myAnimeListAnime.setStartedAiringDate(tmp[0]);
                    myAnimeListAnime.setFinishedAiringDate(tmp[1]);
                }catch(Exception e){
                    log.debug("Error parsing airing/finishing dates");
                }
            }

            if (div.text().startsWith("Producers: "))
                myAnimeListAnime.setProducers(div.text().substring(11, div.text().length()).replace(", ", ",").split(","));

            if (div.text().startsWith("Genres: "))
                myAnimeListAnime.setGenres(div.text().substring(8, div.text().length()).replace(", ", ",").split(","));

            if (div.text().startsWith("Authors: ")){

                String parts[] = div.text().substring(9, div.text().length()).replace(", ", ",").split(",");
                ArrayList<String> listItems = new ArrayList<String>();

                List<MyAnimeListAuthor> authors = myAnimeListAnime.getAuthors();
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

            if (div.text().startsWith("Duration: "))
                myAnimeListAnime.setEpisodeLength(div.text().substring(10, div.text().length()));

            if (div.text().startsWith("Rating: "))
                myAnimeListAnime.setAgeRating(div.text().substring(8, div.text().length()));

            if (div.text().startsWith("Score: "))
                myAnimeListAnime.setScore(div.text().substring(7, div.text().length()));

            if (div.text().startsWith("Ranked: "))
                myAnimeListAnime.setRank(div.text().substring(8, div.text().length()));

            if (div.text().startsWith("Popularity: "))
                myAnimeListAnime.setPopularity(div.text().substring(12, div.text().length()));

            if (div.text().startsWith("Duration: "))
                myAnimeListAnime.setEpisodeLength(div.text().substring(10, div.text().length()));

            if (div.text().startsWith("Type: "))
                myAnimeListAnime.setShowType(div.text().substring(6, div.text().length()));

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
                myAnimeListAnime.setTags(tags);
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
                                character.setFirstName(parts[0]);
                                character.setLastName(parts[1]);
                            }else {
                                if (parts.length == 1)
                                    character.setLastName(characterFullName);
                            }
                            character.setRole(role);
                            myAnimeListAnime.getCharacters().add(character);

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
                                myAnimeListAnime.getAuthors().add(author);

                                log.info(String.format("Add new author %s", author.toString()));
                            }catch (Exception e){
                                log.debug("Error when trying to get author's name");
                            }
                        }

                    }
                }
            }
        }

        return myAnimeListAnime;
    }

    @Override
    public void call() {

    }
}
