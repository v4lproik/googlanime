package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.service.api.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AnimeRepository implements AnimeDAO {

    private static final Logger log = LoggerFactory.getLogger(AnimeRepository.class);
    public final SessionFactory sessionFactory;

    @Autowired
    public AnimeRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(AnimeModel anime) {
        Transaction tx = currentSession().beginTransaction();
        Object idSave = currentSession().save(anime);
        tx.commit();

        return (Long) idSave;
    }

    @Override
    public void saveOrUpdate(AnimeModel anime) {
        long id = anime.getId();
        Transaction tx;

        // Get the entities that need to be updated
        tx=currentSession().beginTransaction();

        Set<AuthorModel> authors = new HashSet<>();
        if (anime.getAuthors() != null){
            for (AuthorModel author:anime.getAuthors()){
                Criteria criteria = currentSession().createCriteria(AuthorModel.class);
                if (author.getFirstName() != null) criteria.add(Restrictions.eq("firstName", author.getFirstName()));
                if (author.getLastName() != null) criteria.add(Restrictions.eq("lastName", author.getLastName()));
                if (author.getBiography() != null) criteria.add(Restrictions.eq("biography", author.getBiography()));
                AuthorModel authorRes = (AuthorModel) criteria.uniqueResult();
                if (authorRes != null){
                    authors.add(authorRes);
                }else {
                    authors.add(author);
                }
            }
        }
        anime.setAuthors(authors);


        Set<CharacterModel> characters = new HashSet<>();
        if (anime.getCharacters() != null){
            for (CharacterModel character:anime.getCharacters()){
                Criteria criteria = currentSession().createCriteria(CharacterModel.class);
                if (character.getFirstName() != null) criteria.add(Restrictions.eq("firstName", character.getFirstName()));
                if (character.getLastName() != null) criteria.add(Restrictions.eq("lastName", character.getLastName()));
                CharacterModel characterRes = (CharacterModel) criteria.uniqueResult();

                if (characterRes != null){
                    characters.add(characterRes);
                }else {
                    characters.add(character);
                }
            }
        }
        anime.setCharacters(characters);


        Set<GenreModel> genres = new HashSet<>();
        if (anime.getGenres() != null){
            for (GenreModel genre:anime.getGenres()){
                Criteria criteria = currentSession().createCriteria(GenreModel.class);
                criteria.add(Restrictions.eq("name", genre.getName()));
                GenreModel genreRes = (GenreModel) criteria.uniqueResult();
                if (genreRes != null){
                    genres.add(genreRes);
                }else {
                    genres.add(genre);
                }
            }
        }

        anime.setGenres(genres);


        Set<ProducerModel> producers = new HashSet<>();
        if (anime.getProducers() != null){
            for (ProducerModel producer:anime.getProducers()){
                Criteria criteria = currentSession().createCriteria(ProducerModel.class);
                criteria.add(Restrictions.eq("name", producer.getName()));
                ProducerModel producerRes = (ProducerModel) criteria.uniqueResult();
                if (producerRes != null){
                    producers.add(producerRes);
                }else {
                    producers.add(producer);
                }
            }
        }

        anime.setProducers(producers);


        Set<TagModel> tags = new HashSet<>();
        if (anime.getTags() != null){
            for (TagModel tag:anime.getTags()){
                Criteria criteria = currentSession().createCriteria(TagModel.class);
                criteria.add(Restrictions.eq("name", tag.getName()));
                TagModel tagRes = (TagModel) criteria.uniqueResult();
                if (tagRes != null){
                    tags.add(tagRes);
                }else {
                    tags.add(tag);
                }
            }
        }

        anime.setTags(tags);



        Set<SynonymModel> synonyms = new HashSet<>();
        if (anime.getSynonyms() != null){
            for (SynonymModel synonym:anime.getSynonyms()){
                Criteria criteria = currentSession().createCriteria(SynonymModel.class);
                criteria.add(Restrictions.eq("title", synonym.getTitle()));
                criteria.add(Restrictions.eq("anime.id", id));
                SynonymModel synonymRes = (SynonymModel) criteria.uniqueResult();

                if (synonymRes != null){
                    synonyms.add(synonymRes);
                }else {
                    synonyms.add(synonym);
                }
            }
        }

        anime.setSynonyms(synonyms);

        // Avoid NonUniqueObjectException has the anime object has been already passed
        currentSession().flush();
        currentSession().clear();
        tx.commit();


        // -----------------------------------------------------------------------------------------
        // Save Anime
        log.debug(anime.toString());
        tx=currentSession().beginTransaction();
        currentSession().saveOrUpdate(anime);

        // -----------------------------------------------------------------------------------------
        // Save OneToMany and ManyToMany with extra columns

        if (anime.getAuthors() != null){
            for (AuthorModel author:anime.getAuthors()) {
                final Integer idAuthor = author.getId();

                List<String> jobs = author.getJobs();
                if (jobs != null){
                    for (String job:jobs){
                        AnimeJobAuthor animeJobAuthor = new AnimeJobAuthor();
                        animeJobAuthor.setIdAnime(id);
                        animeJobAuthor.setIdAuthor(idAuthor);
                        animeJobAuthor.setJob(job);

                        Criteria criteria = currentSession().createCriteria(AnimeJobAuthor.class);
                        criteria.add(Restrictions.eq("idAnime", id));
                        criteria.add(Restrictions.eq("idAuthor", idAuthor));
                        AnimeJobAuthor relationAnimeJobAuthor = (AnimeJobAuthor) criteria.uniqueResult();

                        if (relationAnimeJobAuthor != null){
                            relationAnimeJobAuthor.setJob(job);
                            currentSession().update(relationAnimeJobAuthor);
                        }
                    }
                }
            }
        }

        if (anime.getCharacters() != null){
            for (CharacterModel character:anime.getCharacters()) {
                final Integer idCharacter = character.getId();


                String role = character.getRole();
                if (role != null){
                    AnimeRoleCharacter animeRoleCharacter = null;
                    animeRoleCharacter = new AnimeRoleCharacter();
                    animeRoleCharacter.setIdAnime(id);
                    animeRoleCharacter.setIdCharacter(idCharacter);
                    animeRoleCharacter.setRole(role);

                    Criteria criteria = currentSession().createCriteria(AnimeRoleCharacter.class);
                    criteria.add(Restrictions.eq("idAnime", id));
                    criteria.add(Restrictions.eq("idCharacter", idCharacter));
                    AnimeRoleCharacter relationAnimeRoleCharacter = (AnimeRoleCharacter) criteria.uniqueResult();

                    if (relationAnimeRoleCharacter != null){
                        relationAnimeRoleCharacter.setRole(role);
                        currentSession().update(relationAnimeRoleCharacter);
                    }
                }
            }
        }


        // Avoid NonUniqueObjectException has the anime object has been already passed
        currentSession().flush();
        currentSession().clear();
        tx.commit();


        // -----------------------------------------------------------------------------------------
        // Save dependencies
        if (anime.getSequels() != null){
            for (AnimeIdModel sequel:anime.getSequels()){
                tx=currentSession().beginTransaction();
                currentSession().merge(sequel);
                tx.commit();
            }
        }

        if (anime.getAlternativeVersions() != null){
            for (AnimeIdModel alter:anime.getAlternativeVersions()){
                tx=currentSession().beginTransaction();
                currentSession().merge(alter);
                tx.commit();
            }
        }

        if (anime.getPrequels() != null){
            for (AnimeIdModel prequel:anime.getPrequels()){
                tx=currentSession().beginTransaction();
                currentSession().merge(prequel);
                tx.commit();
            }
        }

        if (anime.getSpinoff() != null){
            for (AnimeIdModel spinOff:anime.getSpinoff()){
                tx=currentSession().beginTransaction();
                currentSession().merge(spinOff);
                tx.commit();
            }
        }

        if (anime.getSideStories() != null){
            for (AnimeIdModel sideStory:anime.getSideStories()){
                tx=currentSession().beginTransaction();
                currentSession().merge(sideStory);
                tx.commit();
            }
        }

        if (anime.getOthers() != null){
            for (AnimeIdModel other:anime.getOthers()){
                tx=currentSession().beginTransaction();
                currentSession().merge(other);
                tx.commit();
            }
        }

        if (anime.getSummaries() != null){
            for (AnimeIdModel summary:anime.getSummaries()){
                tx=currentSession().beginTransaction();
                currentSession().merge(summary);
                tx.commit();
            }
        }

        if (anime.getAdaptations() != null){
            for (AnimeIdModel adaptation:anime.getAdaptations()) {
                tx=currentSession().beginTransaction();
                currentSession().merge(adaptation);
                tx.commit();
            }
        }
    }

    @Override
    public AnimeModel find(Long id){

        Transaction tx=currentSession().beginTransaction();
        Criteria criteria = currentSession().createCriteria(AnimeModel.class);
        criteria.add(Restrictions.eq("id", id));
        AnimeModel animeRes = (AnimeModel) criteria.uniqueResult();
        tx.commit();

        return animeRes;
    }

    @Override
    public void delete(AnimeModel anime) {
        Transaction tx=currentSession().beginTransaction();
        currentSession().delete(anime);
        tx.commit();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

}

