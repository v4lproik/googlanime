package net.v4lproik.googlanime.service.api;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

import static org.elasticsearch.common.lang3.StringUtils.countMatches;

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
        final String type = opts.getType();
        final String name = opts.getQuery();

        MyAnimeListAnime myAnimeListAnime = new MyAnimeListAnime();
        String url = DOMAIN + type + ".php?q=" + name;

        log.debug("Trying to get result from ");

        final Connection.Response response = Jsoup.connect(url).userAgent(USER_AGENT).execute();

        //get list or anime ?
        if(response.url().toString().startsWith(DOMAIN + "anime/") && countMatches(response.url().toString(), "/") == 5){
            Document doc = response.parse();
            myAnimeListAnime = scrapGeneralInformation(doc, url);
        }else{

        }

        return myAnimeListAnime;
    }

    private MyAnimeListAnime scrapGeneralInformation(Document doc, String url){
        MyAnimeListAnime myAnimeListAnime = new MyAnimeListAnime();

        //parse for general information
        Elements tds = doc.select("td");
        for (Element td : tds) {
            if (td.text().startsWith("EditSynopsis")) {
                myAnimeListAnime.setSynopsis(td.text().substring(12, td.text().length()));
            }
        }

        Elements divs = doc.select("div");

        for (Element div : divs) {
            if (div.text().startsWith("Synopsis: "))
                myAnimeListAnime.setSynopsis(div.text().substring(9, div.text().length()));

            if (div.text().startsWith("English: "))
                myAnimeListAnime.setEnglishTitle(div.text().substring(9, div.text().length()));

            if (div.text().startsWith("Japanese: "))
                myAnimeListAnime.setJapaneseTitle(div.text().substring(10, div.text().length()));

            if (div.text().startsWith("Episodes: "))
                myAnimeListAnime.setEpisodeCount(div.text().substring(10, div.text().length()));

            if (div.text().startsWith("Aired: ")) {
                String[] tmp;
                tmp = div.text().substring(7, div.text().length()).split("to ");
                try {
                    myAnimeListAnime.setStartedAiringDate(tmp[0]);
                    myAnimeListAnime.setFinishedAiringDate(tmp[1]);
                }catch(Exception e){

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

        return myAnimeListAnime;
    }

    @Override
    public void call() {

    }
}
