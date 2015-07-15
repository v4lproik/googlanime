package net.v4lproik.googlanime.service.api.common;

import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;

import java.io.IOException;
import java.util.List;

/**
 * Created by joel on 08/05/2015.
 */
public abstract class WebsiteAbstract {
    /**
     * This functions aims to crawl an anime through the website
     * @param opts contains the type manga or anime and the query's name
     * @return a MyAnimeListAnimes. Its one object that contains all the depedencies
     * @throws IOException
     */
    public abstract MyAnimeListAnime crawl(ImportOptions opts) throws IOException;

    /**
     * This functions aims to crawl an anime through the website by id
     * @param opts contains the type manga or anime and the query's name
     * @return
     * @throws IOException
     */
    public abstract MyAnimeListAnime crawlById(ImportOptions opts) throws IOException;

    /**
     * This functions aims to crawl an anime through the website by id
     * @param opts contains the type manga or anime and the query's name
     * @return
     * @throws IOException
     */
    public abstract List<MyAnimeListAnimeDependency> crawlByIdList(ImportOptions opts) throws IOException;

    /**
     * This function aims to call an API
     */
    public abstract void call();

}
