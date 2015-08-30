package net.v4lproik.googlanime.mvc.models;

public class FactoryWebsite {

    public AbstractWebsite getWebsite(Website website){
        switch (website)
        {
            case MAL:
                return new MyAnimeList();

            default: return null;
        }
    }
}
