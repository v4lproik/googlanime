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

    public MangaModel transformMyAnimeListMangaDependencyToDAO(MyAnimeListMangaDependency myAnimeListMangaDependency){
        MangaModel manga = new MangaModel();

        System.out.println(myAnimeListMangaDependency.toString());

        ModelMapper modelMapper = new ModelMapper();
        manga = modelMapper.map(myAnimeListMangaDependency, MangaModel.class);

        String[] genres = myAnimeListMangaDependency.getGenres();
        if (genres != null){
            Set<GenreModel> genresModel = new HashSet<>();
            for (String genre:genres){
                GenreModel genreModel = new GenreModel();
                genreModel.setName(genre);
                genresModel.add(genreModel);
            }
            manga.setGenres(genresModel);
        }

//        String[] producers = myAnimeListMangaDependency.getProducers();
//        if (producers != null){
//            Set<ProducerModel> producersModel = new HashSet<>();
//            for (String producer:producers){
//                ProducerModel producerModel = new ProducerModel();
//                producerModel.setName(producer);
//                producersModel.add(producerModel);
//            }
//            manga.setProducers(producersModel);
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
            manga.setCharacters(charactersModel);
        }


        if (myAnimeListMangaDependency.getFinishedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListMangaDependency.getFinishedAiringDate());
            } catch (ParseException e) {
                manga.setFinishedAiringDate(null);
            }
        }

        if (myAnimeListMangaDependency.getStartedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListMangaDependency.getStartedAiringDate());
            } catch (ParseException e) {
                manga.setStartedAiringDate(null);
            }
        }

        String[] synonyms = myAnimeListMangaDependency.getSynonyms();
        if (synonyms != null) {
            SynonymModel synonymModel;
            Set<SynonymModel> synonymsModel = new HashSet<>();
            for (String synonym : synonyms) {
                synonymModel = new SynonymModel();
                synonymModel.setTitle(synonym);
                synonymModel.setEntry(manga);
                synonymsModel.add(synonymModel);
            }
            manga.setSynonyms(synonymsModel);
        }

        return manga;
    }

    public MangaModel transformMyAnimeListMangaToDAO(MyAnimeListManga myAnimeListManga){
        MangaModel manga = new MangaModel();

        System.out.println(myAnimeListManga.toString());

        ModelMapper modelMapper = new ModelMapper();
        manga = modelMapper.map(myAnimeListManga, MangaModel.class);

        String[] genres = myAnimeListManga.getGenres();
        if (genres != null){
            Set<GenreModel> genresModel = new HashSet<>();
            for (String genre:genres){
                GenreModel genreModel = new GenreModel();
                genreModel.setName(genre);
                genresModel.add(genreModel);
            }
            manga.setGenres(genresModel);
        }

//        String[] producers = myAnimeListManga.getProducers();
//        if (producers != null){
//            Set<ProducerModel> producersModel = new HashSet<>();
//            for (String producer:producers){
//                ProducerModel producerModel = new ProducerModel();
//                producerModel.setName(producer);
//                producersModel.add(producerModel);
//            }
//            manga.setProducers(producersModel);
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
            manga.setCharacters(charactersModel);
        }


        if (myAnimeListManga.getFinishedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListManga.getFinishedAiringDate());
            } catch (ParseException e) {
                manga.setFinishedAiringDate(null);
            }
        }

        if (myAnimeListManga.getStartedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListManga.getStartedAiringDate());
            } catch (ParseException e) {
                manga.setStartedAiringDate(null);
            }
        }

        String[] synonyms = myAnimeListManga.getSynonyms();
        if (synonyms != null) {
            SynonymModel synonymModel;
            Set<SynonymModel> synonymsModel = new HashSet<>();
            for (String synonym : synonyms) {
                synonymModel = new SynonymModel();
                synonymModel.setTitle(synonym);
                synonymModel.setEntry(manga);
                synonymsModel.add(synonymModel);
            }
            manga.setSynonyms(synonymsModel);
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
            manga.setTags(tagsModel);
        }

        return manga;
    }
}
