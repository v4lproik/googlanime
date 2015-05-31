package net.v4lproik.googlanime.service.api;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.common.lang3.StringUtils.countMatches;
import static org.elasticsearch.common.lang3.StringUtils.substringBetween;

/**
 * Created by joel on 08/05/2015.
 */
public class MyAnimeList extends WebsiteAbstract {

    static Logger log = Logger.getLogger(MyAnimeList.class.getName());

    public static final String DOMAIN = "http://myanimelist.net/";
    public static final String API = "";
    public static final String USER_AGENT = "iMAL-iOS";
    public static final String[] URL_GRAB = new String[]{"characters", "characters#staff"};

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
            myAnimeListAnime = scrapGeneralInformation(doc, url, type);
        }

        return myAnimeListAnime;
    }

    @Override
    public MyAnimeListAnime crawlById(ImportOptions opts) throws IOException {

        final String type = opts.getType();
        final String id = opts.getId().toString();
        final String url = DOMAIN + type + "/" + id;

        if (type == null || id == null)
            throw new IllegalArgumentException("Both type and id argument cannot be null");

        if (type.isEmpty() || id.isEmpty())
            throw new IllegalArgumentException("Both type and id argument cannot be empty");

        Document doc = getResultFromJSoup(url, type);


        if(doc == null)
            throw new IOException("No data to be parsed");

        return scrapGeneralInformation(doc, url, type);
    }

    protected Document getResultFromJSoup(String url, String type) throws IOException {
        log.debug("Trying to get result from " + url);

        final Connection.Response response = Jsoup.connect(url).userAgent(USER_AGENT).execute();

        Document doc = null;
        if(response.url().toString().startsWith(DOMAIN + type + "/") && countMatches(response.url().toString(), "/") == 4){
            doc = response.parse();
        }

        return doc;
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

    private MyAnimeListAnime scrapGeneralInformation(Document doc, String url, String type){
        String pattern;
        MyAnimeListAnime myAnimeListAnime = new MyAnimeListAnime();

        //get id
        myAnimeListAnime.setId(this.getIdFromLink(url));

        //get main title
        myAnimeListAnime.setTitle(doc.select("h1").first().ownText());

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

                            if (id != null){
                                MyAnimeListAnime sequel = new MyAnimeListAnime();
                                sequel.setId(id);
                                sequel.setTitle(title);

                                //get sequels
                                List<Object> sequels = myAnimeListAnime.getSequels();
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

                                    if (id != null) {
                                        MyAnimeListAnime sideStory = new MyAnimeListAnime();
                                        sideStory.setId(id);
                                        sideStory.setTitle(title);

                                        //get sequels
                                        List<Object> sideStories = myAnimeListAnime.getSideStories();
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

                                        if (id != null) {
                                            MyAnimeListAnime spinOff = new MyAnimeListAnime();
                                            spinOff.setId(id);
                                            spinOff.setTitle(title);

                                            //get sequels
                                            List<Object> spinOffs = myAnimeListAnime.getSpinoff();
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

                                            if (id != null) {
                                                MyAnimeListAnime other = new MyAnimeListAnime();
                                                other.setId(id);
                                                other.setTitle(title);

                                                //get sequels
                                                List<Object> others = myAnimeListAnime.getOthers();
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

                                                if (id != null) {
                                                    MyAnimeListAnime prequel = new MyAnimeListAnime();
                                                    prequel.setId(id);
                                                    prequel.setTitle(title);

                                                    //get sequels
                                                    List<Object> prequels = myAnimeListAnime.getPrequels();
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

                                                    if (id != null) {
                                                        MyAnimeListAnime alternativeVersion = new MyAnimeListAnime();
                                                        alternativeVersion.setId(id);
                                                        alternativeVersion.setTitle(title);

                                                        //get sequels
                                                        List<Object> alternativeVersions = myAnimeListAnime.getOthers();
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

                                                        if (id != null) {
                                                            MyAnimeListAnime adaptations = new MyAnimeListAnime();
                                                            adaptations.setId(id);
                                                            adaptations.setTitle(title);

                                                            //get sequels
                                                            List<Object> alternativeVersions = myAnimeListAnime.getAdaptations();
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

                                                            if (id != null) {
                                                                MyAnimeListAnime summary = new MyAnimeListAnime();
                                                                summary.setId(id);
                                                                summary.setTitle(title);

                                                                //get sequels
                                                                List<Object> summaries = myAnimeListAnime.getSummaries();
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

                for (int i = 0; i < parts.length; i = i+2) {
                    listItems.add(parts[i] + ", " + parts[i+1]);
                }

                myAnimeListAnime.setAuthors(listItems.toArray(new String[0]));
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
//            log.debug(h2.text());
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
                }
            }
        }

        return myAnimeListAnime;
    }

    @Override
    public void call() {

    }

    public String test(){
        System.out.println("You have called test");
        return "original test";
    }
}
