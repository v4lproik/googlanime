package net.v4lproik.googlanime.service.api.common;

import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListEntryDependency;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListEntry;

import java.io.IOException;
import java.util.List;

public abstract class WebsiteAbstract {
    /**
     * This functions aims to crawl an anime through the website
     * @param opts contains the type manga or anime and the query's name
     * @return a MyAnimeListAnimes. Its one object that contains all the depedencies
     * @throws IOException
     */
    public abstract MyAnimeListEntry crawl(ImportOptions opts) throws IOException;

    /**
     * This functions aims to crawl an anime through the website by id
     * @param opts contains the type manga or anime and the query's name
     * @return
     * @throws IOException
     */
    public abstract MyAnimeListEntry crawlById(ImportOptions opts) throws IOException;

    /**
     * This functions aims to crawl an anime through the website by id
     * @param opts contains the type manga or anime and the query's name
     * @return
     * @throws IOException
     */
    public abstract List<MyAnimeListEntryDependency> crawlByIdList(ImportOptions opts) throws IOException;

    /**
     * This function aims to call an API
     */
    public abstract void call();

}
