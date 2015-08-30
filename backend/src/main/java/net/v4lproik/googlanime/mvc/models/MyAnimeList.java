package net.v4lproik.googlanime.mvc.models;

import net.v4lproik.googlanime.service.api.entities.Entry;
import net.v4lproik.googlanime.service.api.utils.TransformAnimeMapper;
import net.v4lproik.googlanime.service.api.utils.TransformMangaMapper;
import com.github.v4lproik.myanimelist.api.impl.AnimeMangaInformation;
import com.github.v4lproik.myanimelist.api.models.TypeEnum;
import com.github.v4lproik.myanimelist.entities.Anime;
import com.github.v4lproik.myanimelist.entities.AnimeDependency;
import com.github.v4lproik.myanimelist.entities.Manga;
import com.github.v4lproik.myanimelist.entities.MangaDependency;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MyAnimeList extends AbstractWebsite {

    @Autowired
    TransformAnimeMapper animeMapper;

    @Autowired
    TransformMangaMapper mangaMapper;

    @Override
    public Entry crawl(String name, String type) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    public Entry crawl(Integer id, String type) throws IOException {
        com.github.v4lproik.myanimelist.entities.Entry entry = new AnimeMangaInformation().crawl(id, TypeEnum.fromValue(type));

        Entry transformedEntry;

        if (entry.isAnime()) {
            return animeMapper.transformMyAnimeListAnimeToDAO((Anime) entry);
        }else if (TypeEnum.fromValue(type) == TypeEnum.MANGA)
            return mangaMapper.transformMyAnimeListMangaToDAO((Manga) entry);

        return null;
    }

    @Override
    public Set<Entry> crawlAndDependencies(Integer id, String type) throws IOException {
        Set<com.github.v4lproik.myanimelist.entities.EntryDependency> entries = new AnimeMangaInformation().crawlAndDependencies(id, TypeEnum.fromValue(type));
        Set<Entry> transformedEntries = new HashSet<>();

        Entry transformedEntry;

        for (com.github.v4lproik.myanimelist.entities.EntryDependency entry:entries) {
            if (entry.isAnime()) {
                transformedEntries.add(animeMapper.transformMyAnimeListAnimeDependencyToDAO((AnimeDependency) entry));
            } else if (TypeEnum.fromValue(type) == TypeEnum.MANGA)
                transformedEntries.add(mangaMapper.transformMyAnimeListMangaDependencyToDAO((MangaDependency) entry));
        }

        return transformedEntries;
    }

    @Override
    public void call() {
        throw new NotImplementedException();
    }
}
