package net.v4lproik.googlanime.mvc.controllers;

import net.v4lproik.googlanime.mvc.models.JSONResponse;
import net.v4lproik.googlanime.mvc.models.Website;
import net.v4lproik.googlanime.service.api.entities.AnimeModel;
import net.v4lproik.googlanime.service.api.AnimeServiceRead;
import net.v4lproik.googlanime.service.api.myanimelist.models.MyAnimeListAnimeDependency;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/animes")
public class AnimeController {

    static Logger log = Logger.getLogger(AnimeController.class.getName());

    @Autowired
    private AnimeServiceRead service;

    @RequestMapping(value = "", method = RequestMethod.GET, params = {"query", "fields", "type", "render"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam(value = "query", required = true) String query,
                             @RequestParam(value = "type", required = true) String[] type,
                             @RequestParam(value = "fields", required = true) String[] fields,
                             @RequestParam(value = "render", required = false) String render) throws IOException {

        log.debug(String.format("/animes?query=%s&fields=%s&type=%s&render=%s", query, Arrays.asList(fields), Arrays.asList(type), render));

        Class<?> toCast = AnimeModel.class;
        JSONResponse response = new JSONResponse();

        if (Website.containsValue(render) != null){
            switch (Website.containsValue(render)) {
                case MAL:
                    toCast = MyAnimeListAnimeDependency.class;
                    break;
            }
        }

        try {
            List<?> animes = service.find(query, type, fields, toCast);
            response.setAnimes(animes);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setError(e.getMessage());
        }

        return response;
    }
}
