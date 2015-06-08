package net.v4lproik.googlanime.mvc.controllers;

import net.v4lproik.googlanime.mvc.models.AnimeModel;
import net.v4lproik.googlanime.mvc.models.JSONResponse;
import net.v4lproik.googlanime.service.api.AnimeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/animes")
public class AnimeController {

    static Logger log = Logger.getLogger(AnimeController.class.getName());

    @Autowired
    private AnimeService service;

    @RequestMapping(value = "", method = RequestMethod.GET, params = {"query", "fields"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam(value = "query", required = true) String query,
                             @RequestParam(value = "fields", required = true) String[] fields) throws IOException {

        JSONResponse response = new JSONResponse();

        List<AnimeModel> animes = service.find(query, fields);

        return response;
    }
}
