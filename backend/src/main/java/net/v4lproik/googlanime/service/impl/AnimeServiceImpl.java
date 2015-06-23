package net.v4lproik.googlanime.service.impl;

import com.google.gson.Gson;
import net.v4lproik.googlanime.service.api.AnimeModel;
import net.v4lproik.googlanime.service.api.AnimeService;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.fuzzyLikeThisQuery;

@Service
public class AnimeServiceImpl implements AnimeService {

    static Logger log = Logger.getLogger(AnimeServiceImpl.class.getName());

    public static final int MAX_SIZE = 20;

    @Autowired
    private Client client;

    @Override
    public List<AnimeModel> findBySlug(String value) {

        return null;
    }

    @Override
    public List<AnimeModel> find(String query, String[] type, String[] fields) {

        log.debug(String.format("trying to get information from elasticsearch node with %s, %s", query, Arrays.asList(fields)));

        List<AnimeModel> animes = new ArrayList<>();

        QueryBuilder qb = fuzzyLikeThisQuery(fields)
                .likeText(query)
                .maxQueryTerms(12);

        SearchResponse responseElastic = client.prepareSearch("animes", "mangas")
                .setTypes(type)
                .setQuery(qb)
                .setSize(MAX_SIZE)
                .execute()
                .actionGet();

        AnimeModel anime;
        Gson gson = new Gson();

        for (SearchHit hit : responseElastic.getHits()){
            anime = gson.fromJson(hit.getSourceAsString(), AnimeModel.class);
            animes.add(anime);
        }

        return animes;
    }

    @Override
    public List<?> find(String query, String[] type, String[] fields, Class<?> toCast) throws IllegalAccessException, InstantiationException {

        log.debug(String.format("/animes?query=%s&fields=%s&type=%s&render=%s", query, Arrays.asList(fields), Arrays.asList(type), toCast.toString()));

        List<Object> animes = new ArrayList<>();

        QueryBuilder qb = fuzzyLikeThisQuery(fields)
                .likeText(query)
                .maxQueryTerms(12);

        SearchResponse responseElastic = client.prepareSearch("animes", "mangas")
                .setTypes(type)
                .setQuery(qb)
                .setSize(MAX_SIZE)
                .execute()
                .actionGet();

        Object anime = toCast.newInstance();
        Gson gson = new Gson();

        for (SearchHit hit : responseElastic.getHits()){
            anime = gson.fromJson(hit.getSourceAsString(), toCast);
            animes.add(anime);
        }

        return animes;
    }
}
