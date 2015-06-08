package net.v4lproik.googlanime.service.api;

import net.v4lproik.googlanime.mvc.models.AnimeModel;

import java.util.List;

/**
 * Created by joel on 30/04/2015.
 */
public interface AnimeService {
    List<AnimeModel> findBySlug(String query);
    List<AnimeModel> find(String query, String[] fields);
}
