package net.v4lproik.googlanime.service.api;

import net.v4lproik.googlanime.service.api.models.AnimeModel;

public interface AnimeServiceWrite {
    void saveAnime(AnimeModel anime);
    void saveManga(AnimeModel manga);
    void delete(AnimeModel anime);
}
