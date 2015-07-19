package net.v4lproik.googlanime.service.impl;

import net.v4lproik.googlanime.dao.api.MangaDao;
import net.v4lproik.googlanime.service.api.MangaServiceWrite;
import net.v4lproik.googlanime.service.api.entities.MangaModel;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListManga;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListMangaDependency;
import net.v4lproik.googlanime.service.api.utils.TransformMangaMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MangaServiceWriteImpl implements MangaServiceWrite {

    static Logger log = Logger.getLogger(MangaServiceWriteImpl.class.getName());

    public final MangaDao mangaDao;

    public final TransformMangaMapper animeMapper;

    @Autowired
    public MangaServiceWriteImpl(final MangaDao mangaDao, final TransformMangaMapper animeMapper) {
        this.mangaDao = mangaDao;
        this.animeMapper = animeMapper;
    }

    @Transactional( readOnly = false)
    @Override
    public void save(MyAnimeListMangaDependency manga) {
        MangaModel entity = animeMapper.transformMyAnimeListMangaDependencyToDAO(manga);
        if (!isSavable(entity)){
            return;
        }

        log.debug(entity.toString());
        mangaDao.saveOrUpdate(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void save(MyAnimeListManga manga) {
        MangaModel entity = animeMapper.transformMyAnimeListMangaToDAO(manga);
        log.debug(entity.toString());
        mangaDao.save(entity);
    }

    @Transactional( readOnly = false)
    @Override
    public void delete(MyAnimeListManga manga) {
        MangaModel entity = animeMapper.transformMyAnimeListMangaToDAO(manga);
        mangaDao.delete(entity);
    }

    private boolean isSavable(MangaModel anime){
        return anime.getTitle() != null ? true : false;
    }
}
