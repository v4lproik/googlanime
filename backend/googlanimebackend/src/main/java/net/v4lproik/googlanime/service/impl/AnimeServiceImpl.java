package net.v4lproik.googlanime.service.impl;
import net.v4lproik.googlanime.mvc.models.AnimeModel;
import net.v4lproik.googlanime.service.api.AnimeRepository;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.index.query.FilterBuilders.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by joel on 06/05/2015.
 */
@Service
public class AnimeServiceImpl implements AnimeRepository {

    @Autowired
    private Client client;

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

        System.out.println("Current results: " + results.length);
        for (SearchHit hit : results) {
            System.out.println("------------------------------");
            Map<String,Object> result = hit.getSource();
            System.out.println(result);
        }

        return null;
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

        return list;
    }
}
