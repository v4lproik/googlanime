package net.v4lproik.googlanime.dao.repositories;

import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.service.api.models.AnimeModel;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AnimeRepository extends AbstractRepository<AnimeModel, Long> implements AnimeDAO {

    public AnimeRepository(final Class<AnimeModel> klazz, final SessionFactory sessionFactory) {
        super(klazz, sessionFactory);
    }

}

