package net.v4lproik.googlanime.mvc.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.v4lproik.googlanime.Config;
import net.v4lproik.googlanime.mvc.models.BackendException;
import net.v4lproik.googlanime.mvc.models.JSONResponse;
import net.v4lproik.googlanime.mvc.models.Website;
import net.v4lproik.googlanime.mvc.models.WebsiteFactory;
import net.v4lproik.googlanime.service.api.ImportOptions;
import net.v4lproik.googlanime.service.api.WebsiteAbstract;
import net.v4lproik.googlanime.service.api.myanimelist.MyAnimeListAnime;
import net.v4lproik.googlanime.service.api.myanimelist.MyAnimeListAnimeDependency;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/websites")
public class WebsiteController {

    static Logger log = Logger.getLogger(WebsiteController.class.getName());

    @Autowired
    private WebsiteFactory websiteFactory;

    @Autowired
    private Config config;

    @RequestMapping(value = "/import", method = RequestMethod.GET, params={"from", "type", "name"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam(value = "from", required = true) String from,
                             @RequestParam(value = "type", required = true) String type,
                             @RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "dependency", required = false, defaultValue = "false") Boolean dependency) throws BackendException {

        JSONResponse response = new JSONResponse();

        WebsiteAbstract website = websiteFactory.getWebsite(Website.containsValue(from.toUpperCase()));

        if (website == null) {
            response.setError(String.format("Website enum %s not found", from));
            return response;
        }

        try{
            MyAnimeListAnime myAnimeListAnime = website.crawl(new ImportOptions(name, type, dependency));
            log.debug(myAnimeListAnime.toString());
            response.setAnimes(myAnimeListAnime);

            return response;
        }catch (IOException e){
            log.debug("re " + e.getMessage());
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

        WebsiteAbstract website = websiteFactory.getWebsite(Website.containsValue(from.toUpperCase()));

        if (website == null) {
            response.setError(String.format("Website enum %s not found", from));
            return response;
        }

        try{
            final ImportOptions opts = new ImportOptions(id, type, dependency);
            log.debug(String.format("/import with options from=%s, type=%s, id=%s, dependency=%s", from, type, id.toString(), dependency.toString()));

            MyAnimeListAnime myAnimeListAnime = website.crawlById(opts);

            response.setAnimes(myAnimeListAnime);

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

        TransportClient client = config.node();

        WebsiteAbstract website = websiteFactory.getWebsite(Website.containsValue(from.toUpperCase()));

        if (website == null) {
            response.setError(String.format("Website enum %s not found", from));
            return response;
        }

        try{
            final ImportOptions opts = new ImportOptions(id, type, dependency);
            log.debug(String.format("/import with options from=%s, type=%s, id=%s, dependency=%s", from, type, id.toString(), dependency.toString()));

            // List of animes or mangas
            List<MyAnimeListAnimeDependency> animes = website.crawlByIdList(opts);
            response.setAnimes(animes);

            // Insert into elasticsearch
            ObjectMapper mapper = new ObjectMapper();
            IndexResponse elasticResponse = null;

            log.debug(String.format("-----------------ELASTICSEARCH----------------"));

            for (MyAnimeListAnimeDependency anime : animes){
                byte[] json = mapper.writeValueAsBytes(anime);
                log.debug(String.format("---------ANIME-------"));
                log.debug(anime.toString());

                try{
                    if (anime.getType().equals("anime")){
                        elasticResponse = client.prepareIndex("animes", "anime")
                                .setSource(json)
                                .execute()
                                .actionGet();
                    }else
                    {
                        elasticResponse = client.prepareIndex("mangas", "manga")
                                .setSource(json)
                                .execute()
                                .actionGet();
                    }
                }catch (Exception e){
                    log.debug(String.format("[ERROR] Object has NOT been inserted into elasticsearch %s", elasticResponse.toString()));
                }
                log.debug(String.format("Object has been inserted into elasticsearch %s", elasticResponse.toString()));
            }

            return response;
        }catch (IOException e){
            log.error(e.getMessage());
            response.setError(e.getMessage());
        }

        return response;
    }
}
