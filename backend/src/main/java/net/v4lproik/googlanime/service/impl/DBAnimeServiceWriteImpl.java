package net.v4lproik.googlanime.service.impl;

import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.service.api.models.AnimeModel;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class DBAnimeServiceWriteImpl implements AnimeServiceWrite {

    static Logger log = Logger.getLogger(DBAnimeServiceWriteImpl.class.getName());

    public final AnimeDAO animeDAO;

    @Autowired
    public DBAnimeServiceWriteImpl(final AnimeDAO animeDAO) {
        this.animeDAO = animeDAO;
    }

    @Override
    public void saveAnime(AnimeModel anime) {
        animeDAO.save(anime);
    }

    @Override
    public void saveManga(AnimeModel anime) {

        Long res = animeDAO.save(anime);
        System.out.println("Save " + res);

    }

    @Override
    public void delete(AnimeModel anime) {
        animeDAO.delete(anime);
    }
}
