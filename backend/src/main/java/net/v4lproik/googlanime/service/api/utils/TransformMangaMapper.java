package net.v4lproik.googlanime.service.api.utils;

import net.v4lproik.googlanime.service.api.entities.*;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListCharacter;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListManga;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListMangaDependency;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TransformMangaMapper {

    public final static String DATE_FORMAT = "dd-MM-yyyy";

    public AnimeModel transformMyAnimeListMangaDependencyToDAO(MyAnimeListMangaDependency myAnimeListMangaDependency){
        AnimeModel anime = new AnimeModel();

        System.out.println(myAnimeListMangaDependency.toString());

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListMangaDependency, AnimeModel.class);

        String[] genres = myAnimeListMangaDependency.getGenres();
        if (genres != null){
            Set<GenreModel> genresModel = new HashSet<>();
            for (String genre:genres){
                GenreModel genreModel = new GenreModel();
                genreModel.setName(genre);
                genresModel.add(genreModel);
            }
            anime.setGenres(genresModel);
        }

//        String[] producers = myAnimeListMangaDependency.getProducers();
//        if (producers != null){
//            Set<ProducerModel> producersModel = new HashSet<>();
//            for (String producer:producers){
//                ProducerModel producerModel = new ProducerModel();
//                producerModel.setName(producer);
//                producersModel.add(producerModel);
//            }
//            anime.setProducers(producersModel);
//        }

        List<MyAnimeListCharacter> characters = myAnimeListMangaDependency.getCharacters();
        if (characters != null) {
            CharacterModel characterModel;
            Set<CharacterModel> charactersModel = new HashSet<>();
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


        if (myAnimeListMangaDependency.getFinishedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListMangaDependency.getFinishedAiringDate());
            } catch (ParseException e) {
                anime.setFinishedAiringDate(null);
            }
        }

        if (myAnimeListMangaDependency.getStartedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListMangaDependency.getStartedAiringDate());
            } catch (ParseException e) {
                anime.setStartedAiringDate(null);
            }
        }

        String[] synonyms = myAnimeListMangaDependency.getSynonyms();
        if (synonyms != null) {
            SynonymModel synonymModel;
            Set<SynonymModel> synonymsModel = new HashSet<>();
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

    public AnimeModel transformMyAnimeListMangaToDAO(MyAnimeListManga myAnimeListManga){
        AnimeModel anime = new AnimeModel();

        System.out.println(myAnimeListManga.toString());

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListManga, AnimeModel.class);

        String[] genres = myAnimeListManga.getGenres();
        if (genres != null){
            Set<GenreModel> genresModel = new HashSet<>();
            for (String genre:genres){
                GenreModel genreModel = new GenreModel();
                genreModel.setName(genre);
                genresModel.add(genreModel);
            }
            anime.setGenres(genresModel);
        }

//        String[] producers = myAnimeListManga.getProducers();
//        if (producers != null){
//            Set<ProducerModel> producersModel = new HashSet<>();
//            for (String producer:producers){
//                ProducerModel producerModel = new ProducerModel();
//                producerModel.setName(producer);
//                producersModel.add(producerModel);
//            }
//            anime.setProducers(producersModel);
//        }

        List<MyAnimeListCharacter> characters = myAnimeListManga.getCharacters();
        if (characters != null) {
            CharacterModel characterModel;
            Set<CharacterModel> charactersModel = new HashSet<>();
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


        if (myAnimeListManga.getFinishedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListManga.getFinishedAiringDate());
            } catch (ParseException e) {
                anime.setFinishedAiringDate(null);
            }
        }

        if (myAnimeListManga.getStartedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListManga.getStartedAiringDate());
            } catch (ParseException e) {
                anime.setStartedAiringDate(null);
            }
        }

        String[] synonyms = myAnimeListManga.getSynonyms();
        if (synonyms != null) {
            SynonymModel synonymModel;
            Set<SynonymModel> synonymsModel = new HashSet<>();
            for (String synonym : synonyms) {
                synonymModel = new SynonymModel();
                synonymModel.setTitle(synonym);
                synonymModel.setAnime(anime);
                synonymsModel.add(synonymModel);
            }
            anime.setSynonyms(synonymsModel);
        }

        String[] tags = myAnimeListManga.getTags();
        if (tags != null) {
            TagModel tagModel;
            Set<TagModel> tagsModel = new HashSet<>();
            for (String tag : tags) {
                tagModel = new TagModel();
                tagModel.setName(tag);
                tagsModel.add(tagModel);
            }
            anime.setTags(tagsModel);
        }

        return anime;
    }
}
