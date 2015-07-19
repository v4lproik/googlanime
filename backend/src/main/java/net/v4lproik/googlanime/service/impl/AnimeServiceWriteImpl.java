package net.v4lproik.googlanime.service.impl;

import net.v4lproik.googlanime.dao.api.EntryDAO;
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
public class AnimeServiceWriteImpl implements AnimeServiceWrite {

    static Logger log = Logger.getLogger(AnimeServiceWriteImpl.class.getName());

    public final EntryDAO entryDAO;

    public final TransformAnimeMapper animeMapper;

    @Autowired
    public AnimeServiceWriteImpl(final EntryDAO entryDAO, final TransformAnimeMapper animeMapper) {
        this.entryDAO = entryDAO;
        this.animeMapper = animeMapper;
    }

    @Transactional( readOnly = false)
    @Override
    public void save(MyAnimeListAnimeDependency anime) {
        AnimeModel entity = animeMapper.transformMyAnimeListAnimeDependencyToDAO(anime);
        if (!isSavable(entity)){
            return;
        }

        log.debug(entity.toString());
        entryDAO.saveOrUpdate(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void save(MyAnimeListAnime anime) {
        AnimeModel entity = animeMapper.transformMyAnimeListAnimeToDAO(anime);
        log.debug(entity.toString());
        entryDAO.save(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void delete(MyAnimeListAnime anime) {
        AnimeModel entity = animeMapper.transformMyAnimeListAnimeToDAO(anime);
        entryDAO.delete(entity);
    }

    private boolean isSavable(AnimeModel anime){
        return anime.getTitle() != null ? true : false;
    }
}
