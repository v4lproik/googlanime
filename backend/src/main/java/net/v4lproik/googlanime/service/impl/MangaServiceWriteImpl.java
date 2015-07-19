package net.v4lproik.googlanime.service.impl;

import net.v4lproik.googlanime.dao.api.EntryDAO;
import net.v4lproik.googlanime.service.api.MangaServiceWrite;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListManga;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListMangaDependency;
import net.v4lproik.googlanime.service.api.utils.TransformAnimeMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MangaServiceWriteImpl implements MangaServiceWrite {

    static Logger log = Logger.getLogger(MangaServiceWriteImpl.class.getName());

    public final EntryDAO entryDAO;

    public final TransformAnimeMapper animeMapper;

    @Autowired
    public MangaServiceWriteImpl(final EntryDAO entryDAO, final TransformAnimeMapper animeMapper) {
        this.entryDAO = entryDAO;
        this.animeMapper = animeMapper;
    }

    @Transactional( readOnly = false)
    @Override
    public void save(MyAnimeListMangaDependency manga) {
//        AnimeModel entity = animeMapper.transformMyAnimeListAnimeDependencyToDAO(manga);
//        if (!isSavable(entity)){
//            return;
//        }
//
//        log.debug(entity.toString());
//        entryDAO.saveOrUpdate(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void save(MyAnimeListManga manga) {
//        AnimeModel entity = animeMapper.transformMyAnimeListAnimeToDAO(manga);
//        log.debug(entity.toString());
//        entryDAO.save(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void delete(MyAnimeListManga manga) {
//        AnimeModel entity = animeMapper.transformMyAnimeListAnimeToDAO(manga);
//        entryDAO.delete(entity);
    }

    private boolean isSavable(AnimeModel anime){
        return anime.getTitle() != null ? true : false;
    }
}
