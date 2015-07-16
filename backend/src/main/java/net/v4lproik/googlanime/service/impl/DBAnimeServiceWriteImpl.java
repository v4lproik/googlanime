package net.v4lproik.googlanime.service.impl;

import net.v4lproik.googlanime.dao.api.AnimeDAO;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;
import net.v4lproik.googlanime.service.api.utils.TransformAnimeMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DBAnimeServiceWriteImpl implements AnimeServiceWrite {

    static Logger log = Logger.getLogger(DBAnimeServiceWriteImpl.class.getName());

    public final AnimeDAO animeDAO;

    public final TransformAnimeMapper animeMapper;

    @Autowired
    public DBAnimeServiceWriteImpl(final AnimeDAO animeDAO, final TransformAnimeMapper animeMapper) {
        this.animeDAO = animeDAO;
        this.animeMapper = animeMapper;
    }

    @Transactional( readOnly = false)
    @Override
    public void saveAnime(MyAnimeListAnimeDependency anime) {
        AnimeModel entity = animeMapper.transformMyAnimeListAnimeDependencyToDAO(anime);
        log.debug(entity.toString());
        animeDAO.saveOrUpdate(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void saveAnime(MyAnimeListAnime anime) {
        AnimeModel entity = animeMapper.transformMyAnimeListAnimeToDAO(anime);
        log.debug(entity.toString());
        animeDAO.save(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void delete(MyAnimeListAnime anime) {
        AnimeModel entity = animeMapper.transformMyAnimeListAnimeToDAO(anime);
        animeDAO.delete(entity);
    }
}
