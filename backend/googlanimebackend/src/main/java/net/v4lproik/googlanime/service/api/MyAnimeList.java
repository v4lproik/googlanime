package net.v4lproik.googlanime.service.api;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.common.lang3.StringUtils.countMatches;
import static org.elasticsearch.common.lang3.StringUtils.substring;
import static org.elasticsearch.common.lang3.StringUtils.substringBetween;

/**
 * Created by joel on 08/05/2015.
 */
public class MyAnimeList extends WebsiteAbstract {

    static Logger log = Logger.getLogger(MyAnimeList.class.getName());

    public final String DOMAIN = "http://myanimelist.net/";
    public final String API = "";
    public final String USER_AGENT = "iMAL-iOS";
    public final String[] URL_GRAB = new String[]{"characters", "characters#staff"};

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
        MyAnimeListAnime myAnimeListAnime = new MyAnimeListAnime();

        final String type = opts.getType();
        final String id = opts.getId().toString();
        final String url = DOMAIN + type + "/" + id;

        log.debug("Trying to get result from " + url);

        final Connection.Response response = Jsoup.connect(url).userAgent(USER_AGENT).execute();

        if(response.url().toString().startsWith(DOMAIN + type + "/") && countMatches(response.url().toString(), "/") == 4){
            Document doc = response.parse();
            myAnimeListAnime = scrapGeneralInformation(doc, url, type);
        }

        return myAnimeListAnime;
    }

    private Integer getIdFromLinkAbsolute(String link) {
        try {
            return Integer.parseInt(link.split("/")[4].split("/")[0]);
        } catch (Exception e){
            log.debug("Error parsing id");
        }

        return null;
    }

    private Integer getIdFromLinkRelative(String link) {
        try {
            return Integer.parseInt(link.split("/")[2].split("/")[0]);
        } catch (Exception e){
            log.debug("Error parsing id");
        }

        return null;
    }

    private MyAnimeListAnime scrapGeneralInformation(Document doc, String url, String type){
        String pattern;
        MyAnimeListAnime myAnimeListAnime = new MyAnimeListAnime();

        //get id
        myAnimeListAnime.setId(this.getIdFromLinkAbsolute(url));

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
                            Integer id = this.getIdFromLinkRelative(linkHref);

                            if (id != null){
                                MyAnimeListAnime sequel = new MyAnimeListAnime();
                                sequel.setId(id);
                                sequel.setTitle(title);

                                //get sequels
                                List<Object> sequels = myAnimeListAnime.getSequels();
                                sequels.add(sequel);

                                myAnimeListAnime.setSequels(sequels);
                            }
                        }else {
                            if (line.startsWith("Side story:")) {
                                log.debug("Side Stories have been found");

                                Document docTmp = Jsoup.parse(line);
                                Elements links = docTmp.select("a");

                                for (Element link : links) {
                                    String linkHref = link.attr("href");
                                    String title = link.text();
                                    Integer id = this.getIdFromLinkRelative(linkHref);

                                    if (id != null) {
                                        MyAnimeListAnime sideStory = new MyAnimeListAnime();
                                        sideStory.setId(id);
                                        sideStory.setTitle(title);

                                        //get sequels
                                        List<Object> sideStories = myAnimeListAnime.getSideStories();
                                        sideStories.add(sideStory);

                                        myAnimeListAnime.setSideStories(sideStories);
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
                                        Integer id = this.getIdFromLinkRelative(linkHref);

                                        if (id != null) {
                                            MyAnimeListAnime spinOff = new MyAnimeListAnime();
                                            spinOff.setId(id);
                                            spinOff.setTitle(title);

                                            //get sequels
                                            List<Object> spinOffs = myAnimeListAnime.getSpinoff();
                                            spinOffs.add(spinOff);

                                            myAnimeListAnime.setSideStories(spinOffs);
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
                                            Integer id = this.getIdFromLinkRelative(linkHref);

                                            if (id != null) {
                                                MyAnimeListAnime other = new MyAnimeListAnime();
                                                other.setId(id);
                                                other.setTitle(title);

                                                //get sequels
                                                List<Object> others = myAnimeListAnime.getOthers();
                                                others.add(other);

                                                myAnimeListAnime.setSideStories(others);
                                            }
                                        }
                                    }else{
                                        if (line.startsWith("Sequel:")) {
                                            log.debug("Sequels have been found");

                                            Document docTmp = Jsoup.parse(line);
                                            Elements links = docTmp.select("a");

                                            for (Element link : links) {
                                                String linkHref = link.attr("href");
                                                String title = link.text();
                                                Integer id = this.getIdFromLinkRelative(linkHref);

                                                if (id != null) {
                                                    MyAnimeListAnime sequel = new MyAnimeListAnime();
                                                    sequel.setId(id);
                                                    sequel.setTitle(title);

                                                    //get sequels
                                                    List<Object> sequels = myAnimeListAnime.getOthers();
                                                    sequels.add(sequel);

                                                    myAnimeListAnime.setSideStories(sequels);
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

            if (type.equals("manga"))
                pattern = "Published: ";
            else
                pattern = "Aired: ";

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
            }
        }

        return myAnimeListAnime;
    }

    @Override
    public void call() {

    }
}
