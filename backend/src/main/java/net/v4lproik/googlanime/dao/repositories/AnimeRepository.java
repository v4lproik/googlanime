package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.service.api.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

//        tx.commit();
//
//        if (animeRes != null){
//            System.out.println("Update anime " + anime.getId() + " " + anime.getTitle() + " " + anime.getEnglishTitle());
//            tx=currentSession().beginTransaction();
//            currentSession().merge(anime);
//            tx.commit();
//        }else {
//            System.out.println("Save anime " + anime.getId() + " " + anime.getTitle() + " " + anime.getEnglishTitle());
//            tx=currentSession().beginTransaction();
//            currentSession().save(anime);
//            tx.commit();
//        }

        System.out.println("Create anime " + anime.toString());

        tx=currentSession().beginTransaction();
        currentSession().merge(anime);
        currentSession().flush();
        currentSession().clear();
        tx.commit();

        // Save recursive depedencies

        if (anime.getSequels() != null){
            for (AnimeIdModel sequel:anime.getSequels()){
                tx=currentSession().beginTransaction();
                currentSession().merge(sequel);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        if (anime.getAlternativeVersions() != null){
            for (AnimeIdModel alter:anime.getAlternativeVersions()){
                tx=currentSession().beginTransaction();
                currentSession().merge(alter);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        if (anime.getPrequels() != null){
            for (AnimeIdModel prequel:anime.getPrequels()){
                tx=currentSession().beginTransaction();
                currentSession().merge(prequel);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        if (anime.getSpinoff() != null){
            for (AnimeIdModel spinOff:anime.getSpinoff()){
                tx=currentSession().beginTransaction();
                currentSession().merge(spinOff);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        if (anime.getSideStories() != null){
            for (AnimeIdModel sideStory:anime.getSideStories()){
                tx=currentSession().beginTransaction();
                currentSession().merge(sideStory);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        if (anime.getOthers() != null){
            for (AnimeIdModel other:anime.getOthers()){
                tx=currentSession().beginTransaction();
                currentSession().merge(other);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        if (anime.getSummaries() != null){
            for (AnimeIdModel summary:anime.getSummaries()){
                tx=currentSession().beginTransaction();
                currentSession().merge(summary);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        if (anime.getAdaptations() != null){
            for (AnimeIdModel adaptation:anime.getAdaptations()) {
                tx=currentSession().beginTransaction();
                currentSession().merge(adaptation);
                currentSession().flush();
                currentSession().clear();
                tx.commit();
            }
        }

        tx=currentSession().beginTransaction();
        if (anime.getAuthors() != null){
            for (AuthorModel author:anime.getAuthors()) {
                currentSession().saveOrUpdate(author);

                Integer idAuthor = author.getId();

                if (idAuthor != null){
                    AuthorModel authorRes = (AuthorModel) currentSession().get(AuthorModel.class, idAuthor);

                    if (authorRes == null || authorRes != author){
                        idAuthor = (Integer) currentSession().save(author);
                    }
                }

                List<String> jobs = author.getJobs();
                if (jobs != null){
                    for (String job:jobs){
                        AnimeJobAuthor animeJobAuthor = new AnimeJobAuthor();
                        animeJobAuthor.setIdAnime(id);
                        animeJobAuthor.setIdAuthor(idAuthor);
                        animeJobAuthor.setJob(job);

                        currentSession().saveOrUpdate(animeJobAuthor);
                    }
                }
            }
        }

        if (anime.getCharacters() != null){
            for (CharacterModel character:anime.getCharacters()) {
                currentSession().saveOrUpdate(character);

                Integer idCharacter = character.getId();

                if (idCharacter != null){
                    CharacterModel characterRes = (CharacterModel) currentSession().get(CharacterModel.class, idCharacter);

                    if (characterRes == null || characterRes != character){
                        idCharacter = (Integer) currentSession().save(character);
                    }
                }

                String role = character.getRole();
                if (role != null){
                    AnimeRoleCharacter animeRoleCharacter = new AnimeRoleCharacter();
                    animeRoleCharacter.setIdAnime(id);
                    animeRoleCharacter.setIdCharacter(idCharacter);
                    animeRoleCharacter.setRole(role);

                    currentSession().saveOrUpdate(animeRoleCharacter);
                }
            }
        }
        tx.commit();
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

