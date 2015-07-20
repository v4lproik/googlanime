package net.v4lproik.googlanime.service.api;

import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListManga;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListMangaDependency;

public interface MangaServiceWrite {
    void save(MyAnimeListMangaDependency manga);

    void save(MyAnimeListManga manga);

    void delete(MyAnimeListManga manga);
}
