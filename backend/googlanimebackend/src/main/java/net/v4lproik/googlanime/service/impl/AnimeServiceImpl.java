package net.v4lproik.googlanime.service.impl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import net.v4lproik.googlanime.mvc.models.AnimeModel;
import net.v4lproik.googlanime.service.api.AnimeRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.codehaus.jackson.type.TypeReference;


import static org.elasticsearch.index.query.QueryBuilders.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by joel on 06/05/2015.
 */
@Service
public class AnimeServiceImpl implements AnimeRepository {

    @Autowired
    private Client client;

    private Map<String, String> convertJsonToHashMap(String json){
        Map<String, String> result = new HashMap<String, String>();
        String tmp;

        tmp = json.replace("{", "");
        tmp = tmp.replace("}", "");
        tmp = tmp.replace(" ", "");

        for(String str : tmp.split(",")){
            String[] parts;
            parts = str.split("=");
            result.put(parts[0], parts[1]);
        }
        return result;
    }

    private List convertJsonToArray(String json) {

        try {
            return new ObjectMapper().readValue(json, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<AnimeModel> findBySlug(String value) {

        String index = "animes";
        String type = "external";
        String field = "titles.canonical";

        QueryBuilder qb = matchQuery(
                field,
                value
        );

        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(qb)
                .setFrom(0).setSize(60).setExplain(true)
                .execute()
                .actionGet();

        SearchHit[] results = response.getHits().getHits();
        List<AnimeModel> list = new ArrayList<AnimeModel>();

        System.out.println("Current results: " + results.length);
        for (SearchHit hit : results) {
            System.out.println("------------------------------");
            Map<String, Object> result = hit.getSource();
            System.out.println(result);


            AnimeModel anime = new AnimeModel(Integer.parseInt(hit.getId()));
            anime.setCoverImage(result.get("cover_image").toString());
            anime.setPosterImage(result.get("poster_image").toString());
            anime.setSlug(result.get("slug").toString());
            anime.setAgeRating(result.get("age_rating").toString());
            anime.setCommunityRating(result.get("community_rating").toString());
            anime.setEpisodeCount(result.get("episode_count").toString());
            anime.setEpisodeLength(result.get("episode_length").toString());
            anime.setFinishedAiringDate(result.get("finished_airing_date").toString());
            anime.setStartedAiringDate(result.get("started_airing_date").toString());
            anime.setSynopsis(result.get("synopsis").toString());

            final Map<String, String> map = this.convertJsonToHashMap(result.get("titles").toString());
            anime.setRomajiTitle(map.get("romaji"));
            anime.setJapaneseTitle(map.get("japanese"));
            anime.setCanonicalTitle(map.get("canonical"));
            anime.setEnglishTitle(map.get("english"));

//            anime.setGenres(this.convertJsonToArray(result.get("genres").toString()));
            //anime.setLinks(this.convertJsonToHashMap(result.get("links").toString()));

            list.add(anime);
        }

        return list;
    }

    @Override
    public List<AnimeModel> find(Integer id) {
        GetResponse response = client.prepareGet("animes", "external", id.toString())
                .execute()
                .actionGet();

        if( response == null || response.isExists() == false || response.isSourceEmpty() == true )
            return null;

        List<AnimeModel> list = new ArrayList<AnimeModel>();
        Map<String, Object> source = response.getSource();

        System.out.println("------------------------------");
        System.out.println("Index: " + response.getIndex());
        System.out.println("Type: " + response.getType());
        System.out.println("Id: " + response.getId());
        System.out.println("Version: " + response.getVersion());
        System.out.println(source);
        System.out.println("------------------------------");

        AnimeModel anime = new AnimeModel(Integer.parseInt(response.getId()));

        list.add(anime);

        return list;
    }
}
