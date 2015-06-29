package net.v4lproik.googlanime.dao.api;

import net.v4lproik.googlanime.service.api.models.AnimeModel;

public interface AnimeDAO {
    Long save(AnimeModel anime);
    void saveOrUpdate(AnimeModel anime);
    void delete(AnimeModel anime);
}
