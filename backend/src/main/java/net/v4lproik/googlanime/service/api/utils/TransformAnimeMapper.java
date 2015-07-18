package net.v4lproik.googlanime.service.api.utils;

import net.v4lproik.googlanime.service.api.entities.*;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListCharacter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransformAnimeMapper {

    public final static String DATE_FORMAT = "dd-MM-yyyy";

    public AnimeModel transformMyAnimeListAnimeDependencyToDAO(MyAnimeListAnimeDependency myAnimeListAnimeDependency){
        AnimeModel anime = new AnimeModel();

        System.out.println(myAnimeListAnimeDependency.toString());

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

//        AuthorModel authorModel;
//        List<MyAnimeListAuthor> authors = myAnimeListAnimeDependency.getAuthors();
//        if (authors != null) {
//            for (MyAnimeListAuthor author : authors) {
//                authorModel = new AuthorModel();
//                authorModel.setFirstName(author.getFirstName());
//                authorModel.setLastName(author.getLastName());
//                authorModel.setJobs(Arrays.asList(author.getJob()));
//            }
//        }
//
        List<MyAnimeListCharacter> characters = myAnimeListAnimeDependency.getCharacters();
        if (characters != null) {
            CharacterModel characterModel;
            List<CharacterModel> charactersModel = new ArrayList<CharacterModel>();
            for (MyAnimeListCharacter character : characters) {
                characterModel = new CharacterModel();
                characterModel.setRole(character.getRole());
                characterModel.setFirstName(character.getFirstName());
                characterModel.setJapaneseName(character.getJapaneseName());
                characterModel.setLastName(character.getLastName());
                charactersModel.add(characterModel);
            }
            anime.setCharacters(charactersModel);
        }


        if (myAnimeListAnimeDependency.getFinishedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListAnimeDependency.getFinishedAiringDate());
            } catch (ParseException e) {
                anime.setFinishedAiringDate(null);
            }
        }

        if (myAnimeListAnimeDependency.getStartedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListAnimeDependency.getStartedAiringDate());
            } catch (ParseException e) {
                anime.setStartedAiringDate(null);
            }
        }

        String[] synonyms = myAnimeListAnimeDependency.getSynonyms();
        if (synonyms != null) {
            SynonymModel synonymModel;
            List<SynonymModel> synonymsModel = new ArrayList<SynonymModel>();
            for (String synonym : synonyms) {
                synonymModel = new SynonymModel();
                synonymModel.setTitle(synonym);
                synonymModel.setAnime(anime);
                synonymsModel.add(synonymModel);
            }
            anime.setSynonyms(synonymsModel);
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
