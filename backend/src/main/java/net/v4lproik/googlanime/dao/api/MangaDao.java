package net.v4lproik.googlanime.dao.api;

import net.v4lproik.googlanime.service.api.entities.MangaModel;

public interface MangaDao {
    Long save(MangaModel manga);
    MangaModel find(Long id);
    void saveOrUpdate(MangaModel manga);
    void delete(MangaModel manga);
}
