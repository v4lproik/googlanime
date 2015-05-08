package net.v4lproik.googlanime.mvc.models;

import net.v4lproik.googlanime.service.api.MyAnimeList;
import net.v4lproik.googlanime.service.api.Website;
import net.v4lproik.googlanime.service.api.WebsiteAbstract;

/**
 * Created by joel on 08/05/2015.
 */
public class WebsiteFactory {
    public WebsiteAbstract getWebsite(String websiteType){
        if(websiteType == null){
            return null;
        }
        if(websiteType.equalsIgnoreCase("MAL")){
            return new MyAnimeList();
        }
        return null;
    }
}
