package net.v4lproik.googlanime.service.api;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by joel on 08/05/2015.
 */
public abstract class WebsiteAbstract {
    /**
     * This functions aims to crawl an anime through the website
     * @param opts contains the type manga or anime and the query's name
     * @return
     * @throws IOException
     */
    abstract public MyAnimeListAnime crawl(ImportOptions opts) throws IOException;

    /**
     * This function aims to call an API
     */
    abstract public void call();
}
