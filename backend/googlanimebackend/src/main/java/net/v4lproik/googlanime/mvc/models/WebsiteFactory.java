package net.v4lproik.googlanime.mvc.models;

import net.v4lproik.googlanime.service.api.MyAnimeList;
import net.v4lproik.googlanime.service.api.WebsiteAbstract;
import org.springframework.stereotype.Service;

/**
 * Created by joel on 08/05/2015.
 */
@Service
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
