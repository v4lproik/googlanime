package net.v4lproik.googlanime.mvc.controllers;

import net.v4lproik.googlanime.mvc.models.BackendException;
import net.v4lproik.googlanime.mvc.models.JSONResponse;
import net.v4lproik.googlanime.mvc.models.WebsiteFactory;
import net.v4lproik.googlanime.service.api.ImportOptions;
import net.v4lproik.googlanime.service.api.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.Website;
import net.v4lproik.googlanime.service.api.WebsiteAbstract;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by joel on 08/05/2015.
 */

@Controller
@RequestMapping(value = "/websites")
public class WebsiteController {

    static Logger log = Logger.getLogger(WebsiteController.class.getName());

    @Autowired
    private WebsiteFactory websiteFactory;

    @RequestMapping(value = "/import", method = RequestMethod.GET, params={"from", "type", "name"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam(value = "from", required = true) String from,
                             @RequestParam(value = "type", required = true) String type,
                             @RequestParam(value = "name", required = true) String name) throws BackendException {

        JSONResponse response = new JSONResponse();

        WebsiteAbstract website = websiteFactory.getWebsite(from);

        try{
            MyAnimeListAnime myAnimeListAnime = website.crawl(new ImportOptions(name, type));
            log.debug(myAnimeListAnime.toString());
            response.setAnimes(myAnimeListAnime);
            return response;
        }catch (IOException e){
            log.error(e.getMessage());
            response.setError(e.getMessage());
        }

        return response;
    }
}
