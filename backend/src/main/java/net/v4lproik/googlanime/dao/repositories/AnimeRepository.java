package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.service.api.entities.AnimeJobAuthor;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.entities.AuthorModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimeRepository implements AnimeDAO {

    @Autowired
    public SessionFactory sessionFactoryConfig;

    public AnimeRepository() {

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

        Transaction tx=currentSession().beginTransaction();

        currentSession().saveOrUpdate(anime);

        if (anime.getAuthors() != null){
            for (AuthorModel author:anime.getAuthors()){
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
        tx.commit();
    }

    @Override
    public void delete(AnimeModel anime) {
        Transaction tx=currentSession().beginTransaction();
        currentSession().delete(anime);
        tx.commit();
    }

    private Session currentSession() {
        return sessionFactoryConfig.getCurrentSession();
    }

}

