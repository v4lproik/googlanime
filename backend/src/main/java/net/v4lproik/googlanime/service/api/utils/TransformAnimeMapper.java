package net.v4lproik.googlanime.service.api.utils;

import net.v4lproik.googlanime.service.api.entities.*;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAuthor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TransformAnimeMapper {

    public AnimeModel transformMyAnimeListAnimeDependencyToDAO(MyAnimeListAnimeDependency myAnimeListAnimeDependency){
        AnimeModel anime = new AnimeModel();

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListAnimeDependency, AnimeModel.class);

        String[] genres = myAnimeListAnimeDependency.getGenres();
        if (genres != null){
            List<GenreModel> genresModel = new ArrayList<>();
            for (String genre:genres){
                GenreModel genreModel = new GenreModel();
                genreModel.setName(genre);
                genresModel.add(genreModel);
            }
            anime.setGenres(genresModel);
        }

        String[] producers = myAnimeListAnimeDependency.getProducers();
        if (producers != null){
            List<ProducerModel> producersModel = new ArrayList<>();
            for (String producer:producers){
                ProducerModel producerModel = new ProducerModel();
                producerModel.setName(producer);
                producersModel.add(producerModel);
            }
            anime.setProducers(producersModel);
        }

        List<AnimeJobAuthor> jobsAuthor;
        AuthorModel authorModel;
        List<MyAnimeListAuthor> authors = myAnimeListAnimeDependency.getAuthors();
        if (authors != null) {
            for (MyAnimeListAuthor author : authors) {
                authorModel = new AuthorModel();
                authorModel.setFirstName(author.getFirstName());
                authorModel.setLastName(author.getLastName());
                authorModel.setJobs(Arrays.asList(author.getJob()));
//                jobsAuthor = new ArrayList<AnimeJobAuthor>();
//                String[] jobs = author.getJob();
//                if (jobs != null) {
//                    for (String job : jobs) {
//                        AnimeJobAuthor authorJobAnimeModel = new AnimeJobAuthor();
//                        authorJobAnimeModel.setAuthor(authorModel);
//                        authorJobAnimeModel.setAnime(anime);
//                        jobsAuthor.add(authorJobAnimeModel);
//                    }
//                }
//                anime.setAnimeJobAuthors(jobsAuthor);
            }
        }

        return anime;
    }

    public AnimeModel transformMyAnimeListAnimeToDAO(MyAnimeListAnime myAnimeListAnime){
        AnimeModel anime = new AnimeModel();

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListAnime, AnimeModel.class);

        return anime;
    }
}
