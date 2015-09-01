package net.v4lproik.googlanime.mvc.controllers;

import net.v4lproik.googlanime.mvc.models.*;
import net.v4lproik.googlanime.service.api.AnimeServiceWrite;
import net.v4lproik.googlanime.service.api.MangaServiceWrite;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.entities.Entry;
import net.v4lproik.googlanime.service.api.entities.MangaModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Set;

@Controller
@RequestMapping(value = "/websites")
public class WebsiteController {

    static Logger log = Logger.getLogger(WebsiteController.class.getName());

    @Autowired
    private FactoryWebsite factoryWebsite;

    @Autowired
    private AnimeServiceWrite animeServiceWrite;

    @Autowired
    private MangaServiceWrite mangaServiceWrite;

    @RequestMapping(value = "/import", method = RequestMethod.GET, params={"from", "type", "name"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam(value = "from", required = true) String from,
                             @RequestParam(value = "type", required = true) String type,
                             @RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "dependency", required = false, defaultValue = "false") Boolean dependency) throws BackendException {

        JSONResponse response = new JSONResponse();

        AbstractWebsite website = factoryWebsite.getWebsite(Website.containsValue(from.toUpperCase()));

        if (website == null) {
            response.setError(String.format("Website enum %s not found", from));
            return response;
        }

        try{
            Entry entry = website.crawl(name, type);
            response.setAnimes(entry);

            return response;
        }catch (IOException e){
            log.debug(e);
            response.setError(e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET, params={"from", "type", "id"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam(value = "from", required = true) String from,
                             @RequestParam(value = "type", required = true) String type,
                             @RequestParam(value = "id", required = true) Integer id,
                             @RequestParam(value = "dependency", required = false, defaultValue = "false") Boolean dependency) throws BackendException {

        JSONResponse response = new JSONResponse();

        AbstractWebsite website = factoryWebsite.getWebsite(Website.containsValue(from.toUpperCase()));

        if (website == null) {
            response.setError(String.format("Website enum %s not found", from));
            return response;
        }

        try{
            log.debug(String.format("/import with options from=%s, type=%s, id=%s, dependency=%s", from, type, id.toString(), dependency.toString()));

            Entry entry = website.crawl(id, type);
            response.setAnimes(entry);

            return response;
        }catch (IOException e){
            log.error(e.getMessage());
            response.setError(e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "/import/store", method = RequestMethod.GET, params={"from", "type", "id"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse store(@RequestParam(value = "from", required = true) String from,
                             @RequestParam(value = "type", required = true) String type,
                             @RequestParam(value = "id", required = true) Integer id,
                             @RequestParam(value = "dependency", required = false, defaultValue = "true") Boolean dependency) throws BackendException {

        JSONResponse response = new JSONResponse();

        AbstractWebsite website = factoryWebsite.getWebsite(Website.containsValue(from.toUpperCase()));

        if (website == null) {
            response.setError(String.format("Website enum %s not found", from));
            return response;
        }

        try{
            log.debug(String.format("/import/store with options from=%s, type=%s, id=%s, dependency=%s", from, type, id.toString(), dependency.toString()));

            Set<Entry> entries = website.crawlAndDependencies(id, type);
            response.setAnimes(entries);

            for (Entry entity : entries){

                if (AbstractTypeEnum.fromValue(entity.getType()) == AbstractTypeEnum.ANIME){
                    animeServiceWrite.save((AnimeModel) entity);
                }else
                {
                    mangaServiceWrite.save((MangaModel) entity);
                }
            }

        }catch (IOException e){
            log.error(e.getMessage());
            response.setError(e.getMessage());
        }

        return response;
    }
}
