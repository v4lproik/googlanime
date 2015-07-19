package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.dao.api.MangaDao;
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
public class MangaRepository implements MangaDao {

    private static final Logger log = LoggerFactory.getLogger(MangaRepository.class);
    public final SessionFactory sessionFactory;

    @Autowired
    public MangaRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long save(MangaModel manga) {
        Transaction tx = currentSession().beginTransaction();
        Object idSave = currentSession().save(manga);
        tx.commit();

        return (Long) idSave;
    }

    @Override
    public void saveOrUpdate(MangaModel manga) {
        long id = manga.getId();
        Transaction tx;

        // Get the entities that need to be updated
        tx=currentSession().beginTransaction();

        Set<AuthorModel> authors = new HashSet<>();
        if (manga.getAuthors() != null){
            for (AuthorModel author:manga.getAuthors()){
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
        manga.setAuthors(authors);


        Set<CharacterModel> characters = new HashSet<>();
        if (manga.getCharacters() != null){
            for (CharacterModel character:manga.getCharacters()){
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
        manga.setCharacters(characters);


        Set<GenreModel> genres = new HashSet<>();
        if (manga.getGenres() != null){
            for (GenreModel genre:manga.getGenres()){
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

        manga.setGenres(genres);


        Set<TagModel> tags = new HashSet<>();
        if (manga.getTags() != null){
            for (TagModel tag:manga.getTags()){
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

        manga.setTags(tags);



        Set<SynonymModel> synonyms = new HashSet<>();
        if (manga.getSynonyms() != null){
            for (SynonymModel synonym:manga.getSynonyms()){
                Criteria criteria = currentSession().createCriteria(SynonymModel.class);
                criteria.add(Restrictions.eq("title", synonym.getTitle()));
                criteria.add(Restrictions.eq("manga.id", id));
                SynonymModel synonymRes = (SynonymModel) criteria.uniqueResult();

                if (synonymRes != null){
                    synonyms.add(synonymRes);
                }else {
                    synonyms.add(synonym);
                }
            }
        }

        manga.setSynonyms(synonyms);

        // Avoid NonUniqueObjectException has the manga object has been already passed
        currentSession().flush();
        currentSession().clear();
        tx.commit();


        // -----------------------------------------------------------------------------------------
        // Save Anime
        log.debug(manga.toString());
        tx=currentSession().beginTransaction();
        currentSession().saveOrUpdate(manga);

        // -----------------------------------------------------------------------------------------
        // Save OneToMany and ManyToMany with extra columns

        if (manga.getAuthors() != null){
            for (AuthorModel author:manga.getAuthors()) {
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

        if (manga.getCharacters() != null){
            for (CharacterModel character:manga.getCharacters()) {
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


        // Avoid NonUniqueObjectException has the manga object has been already passed
        currentSession().flush();
        currentSession().clear();
        tx.commit();


        // -----------------------------------------------------------------------------------------
        // Save dependencies
        if (manga.getSequels() != null){
            for (AnimeIdModel sequel:manga.getSequels()){
                tx=currentSession().beginTransaction();
                currentSession().merge(sequel);
                tx.commit();
            }
        }

        if (manga.getAlternativeVersions() != null){
            for (AnimeIdModel alter:manga.getAlternativeVersions()){
                tx=currentSession().beginTransaction();
                currentSession().merge(alter);
                tx.commit();
            }
        }

        if (manga.getPrequels() != null){
            for (AnimeIdModel prequel:manga.getPrequels()){
                tx=currentSession().beginTransaction();
                currentSession().merge(prequel);
                tx.commit();
            }
        }

        if (manga.getSpinoff() != null){
            for (AnimeIdModel spinOff:manga.getSpinoff()){
                tx=currentSession().beginTransaction();
                currentSession().merge(spinOff);
                tx.commit();
            }
        }

        if (manga.getSideStories() != null){
            for (AnimeIdModel sideStory:manga.getSideStories()){
                tx=currentSession().beginTransaction();
                currentSession().merge(sideStory);
                tx.commit();
            }
        }

        if (manga.getOthers() != null){
            for (AnimeIdModel other:manga.getOthers()){
                tx=currentSession().beginTransaction();
                currentSession().merge(other);
                tx.commit();
            }
        }

        if (manga.getSummaries() != null){
            for (AnimeIdModel summary:manga.getSummaries()){
                tx=currentSession().beginTransaction();
                currentSession().merge(summary);
                tx.commit();
            }
        }

        if (manga.getAdaptations() != null){
            for (AnimeIdModel adaptation:manga.getAdaptations()) {
                tx=currentSession().beginTransaction();
                currentSession().merge(adaptation);
                tx.commit();
            }
        }
    }

    @Override
    public MangaModel find(Long id){

        Transaction tx=currentSession().beginTransaction();
        Criteria criteria = currentSession().createCriteria(MangaModel.class);
        criteria.add(Restrictions.eq("id", id));
        MangaModel mangaRes = (MangaModel) criteria.uniqueResult();
        tx.commit();

        return mangaRes;
    }

    @Override
    public void delete(MangaModel manga) {
        Transaction tx=currentSession().beginTransaction();
        currentSession().delete(manga);
        tx.commit();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

}

