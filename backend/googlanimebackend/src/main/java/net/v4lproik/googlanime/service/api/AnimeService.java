package net.v4lproik.googlanime.service.api;

import java.util.List;

/**
 * Created by joel on 30/04/2015.
 */
public interface AnimeService {
    List<AnimeModel> findBySlug(String query);
    List<AnimeModel> find(String query, String[] type, String[] fields);
    List<?> find(String query, String[] type, String[] fields, Class<?> toCast) throws IllegalAccessException, InstantiationException;
}
