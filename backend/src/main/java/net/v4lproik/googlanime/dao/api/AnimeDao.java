package net.v4lproik.googlanime.dao.api;

import net.v4lproik.googlanime.service.api.entities.AnimeModel;

public interface AnimeDao {
    Long save(AnimeModel anime);
    AnimeModel find(Long id);
    void saveOrUpdate(AnimeModel anime);
    void delete(AnimeModel anime);
}
