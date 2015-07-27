package net.v4lproik.googlanime.service.api.utils;

import net.v4lproik.googlanime.service.api.entities.*;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAuthor;
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
public class TransformMangaMapper extends TransformAbstractMapper {

    public final static String DATE_FORMAT = "dd-MM-yyyy";

    public MangaModel transformMyAnimeListMangaDependencyToDAO(MyAnimeListMangaDependency myAnimeListMangaDependency){
        MangaModel manga = new MangaModel();

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

                Set<CharacterNicknameModel> characterNicknamesModel = new HashSet<>();
                if (character.getNickNames() != null){
                    for (String nickname:character.getNickNames()){
                        characterNicknamesModel.add(new CharacterNicknameModel(nickname));
                    }
                }
                characterModel.setNicknames(characterNicknamesModel);

                charactersModel.add(characterModel);
            }
            manga.setCharacters(charactersModel);
        }

        List<MyAnimeListAuthor> authors = myAnimeListMangaDependency.getAuthors();
        if (authors != null) {
            AuthorModel authorModel;
            Set<AuthorModel> authorsModel = new HashSet<>();
            for (MyAnimeListAuthor author : authors) {
                authorModel = new AuthorModel();
                authorModel.setFirstName(author.getFirstName());
                authorModel.setLastName(author.getLastName());

                Set<String> jobs = new HashSet<>();
                if (author.getJob() != null){
                    for (String job:author.getJob()){
                        jobs.add(job);
                    }
                }
                authorModel.setJobs(jobs);

                authorsModel.add(authorModel);
            }
            manga.setAuthors(authorsModel);
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

        String[] tags = myAnimeListMangaDependency.getTags();
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

    public MangaModel transformMyAnimeListMangaToDAO(MyAnimeListManga myAnimeListManga){
        MangaModel manga = new MangaModel();

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

                Set<CharacterNicknameModel> characterNicknamesModel = new HashSet<>();
                if (character.getNickNames() != null){
                    for (String nickname:character.getNickNames()){
                        characterNicknamesModel.add(new CharacterNicknameModel(nickname));
                    }
                }
                characterModel.setNicknames(characterNicknamesModel);

                charactersModel.add(characterModel);
            }
            manga.setCharacters(charactersModel);
        }

        List<MyAnimeListAuthor> authors = myAnimeListManga.getAuthors();
        if (authors != null) {
            AuthorModel authorModel;
            Set<AuthorModel> authorsModel = new HashSet<>();
            for (MyAnimeListAuthor author : authors) {
                authorModel = new AuthorModel();
                authorModel.setFirstName(author.getFirstName());
                authorModel.setLastName(author.getLastName());

                Set<String> jobs = new HashSet<>();
                if (author.getJob() != null){
                    for (String job:author.getJob()){
                        jobs.add(job);
                    }
                }
                authorModel.setJobs(jobs);

                authorsModel.add(authorModel);
            }
            manga.setAuthors(authorsModel);
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
