package net.v4lproik.googlanime.service.api.utils;

import net.v4lproik.googlanime.service.api.entities.*;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAuthor;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListCharacter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TransformAnimeMapper extends TransformAbstractMapper {

    public final static String DATE_FORMAT = "dd-MM-yyyy";

    public AnimeModel transformMyAnimeListAnimeDependencyToDAO(MyAnimeListAnimeDependency myAnimeListEntryDependency){
        AnimeModel anime;

        System.out.println(myAnimeListEntryDependency.toString());

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListEntryDependency, AnimeModel.class);

        String[] genres = myAnimeListEntryDependency.getGenres();
        if (genres != null){
            Set<GenreModel> genresModel = new HashSet<>();
            for (String genre:genres){
                GenreModel genreModel = new GenreModel();
                genreModel.setName(genre);
                genresModel.add(genreModel);
            }
            anime.setGenres(genresModel);
        }

        String[] producers = myAnimeListEntryDependency.getProducers();
        if (producers != null){
            Set<ProducerModel> producersModel = new HashSet<>();
            for (String producer:producers){
                ProducerModel producerModel = new ProducerModel();
                producerModel.setName(producer);
                producersModel.add(producerModel);
            }
            anime.setProducers(producersModel);
        }

        List<MyAnimeListCharacter> characters = myAnimeListEntryDependency.getCharacters();
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
            anime.setCharacters(charactersModel);
        }

        List<MyAnimeListAuthor> authors = myAnimeListEntryDependency.getAuthors();
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
            anime.setAuthors(authorsModel);
        }

        if (myAnimeListEntryDependency.getFinishedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListEntryDependency.getFinishedAiringDate());
            } catch (ParseException e) {
                anime.setFinishedAiringDate(null);
            }
        }

        if (myAnimeListEntryDependency.getStartedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListEntryDependency.getStartedAiringDate());
            } catch (ParseException e) {
                anime.setStartedAiringDate(null);
            }
        }

        String[] synonyms = myAnimeListEntryDependency.getSynonyms();
        if (synonyms != null) {
            SynonymModel synonymModel;
            Set<SynonymModel> synonymsModel = new HashSet<>();
            for (String synonym : synonyms) {
                synonymModel = new SynonymModel();
                synonymModel.setTitle(synonym);
                synonymModel.setEntry(anime);
                synonymsModel.add(synonymModel);
            }
            anime.setSynonyms(synonymsModel);
        }

        String[] tags = myAnimeListEntryDependency.getTags();
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

    public AnimeModel transformMyAnimeListAnimeToDAO(MyAnimeListAnime myAnimeListAnime){
        AnimeModel anime;

        ModelMapper modelMapper = new ModelMapper();
        anime = modelMapper.map(myAnimeListAnime, AnimeModel.class);

        String[] genres = myAnimeListAnime.getGenres();
        if (genres != null){
            Set<GenreModel> genresModel = new HashSet<>();
            for (String genre:genres){
                GenreModel genreModel = new GenreModel();
                genreModel.setName(genre);
                genresModel.add(genreModel);
            }
            anime.setGenres(genresModel);
        }

        String[] producers = myAnimeListAnime.getProducers();
        if (producers != null){
            Set<ProducerModel> producersModel = new HashSet<>();
            for (String producer:producers){
                ProducerModel producerModel = new ProducerModel();
                producerModel.setName(producer);
                producersModel.add(producerModel);
            }
            anime.setProducers(producersModel);
        }

        List<MyAnimeListCharacter> characters = myAnimeListAnime.getCharacters();
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
            anime.setCharacters(charactersModel);
        }

        List<MyAnimeListAuthor> authors = myAnimeListAnime.getAuthors();
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
            anime.setAuthors(authorsModel);
        }

        if (myAnimeListAnime.getFinishedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListAnime.getFinishedAiringDate());
            } catch (ParseException e) {
                anime.setFinishedAiringDate(null);
            }
        }

        if (myAnimeListAnime.getStartedAiringDate() != null){
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(myAnimeListAnime.getStartedAiringDate());
            } catch (ParseException e) {
                anime.setStartedAiringDate(null);
            }
        }

        String[] synonyms = myAnimeListAnime.getSynonyms();
        if (synonyms != null) {
            SynonymModel synonymModel;
            Set<SynonymModel> synonymsModel = new HashSet<>();
            for (String synonym : synonyms) {
                synonymModel = new SynonymModel();
                synonymModel.setTitle(synonym);
                synonymModel.setEntry(anime);
                synonymsModel.add(synonymModel);
            }
            anime.setSynonyms(synonymsModel);
        }

        String[] tags = myAnimeListAnime.getTags();
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
