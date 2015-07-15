package net.v4lproik.googlanime.service.api.utils;

import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransformAnimeMapper {

    public AnimeModel transformMyAnimeListAnimeDependencyToDAO(MyAnimeListAnimeDependency myAnimeListAnimeDependency){
        AnimeModel anime = new AnimeModel();

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListAnimeDependency, AnimeModel.class);

        return anime;
    }

    public AnimeModel transformMyAnimeListAnimeToDAO(MyAnimeListAnime myAnimeListAnime){
        AnimeModel anime = new AnimeModel();

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListAnime, AnimeModel.class);

        return anime;
    }
}
