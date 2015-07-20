package net.v4lproik.googlanime.service.api;

import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;

public interface AnimeServiceWrite {
    void save(MyAnimeListAnimeDependency anime);

    void save(MyAnimeListAnime anime);

    void delete(MyAnimeListAnime anime);
}
