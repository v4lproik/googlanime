package net.v4lproik.googlanime.mvc.controllers;

import com.fasterxml.jackson.databind.SerializationFeature;
import net.v4lproik.googlanime.CustomObjectMapper;
import net.v4lproik.googlanime.mvc.models.AnimeModel;
import net.v4lproik.googlanime.service.api.AnimeRepository;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import net.v4lproik.googlanime.mvc.models.JSONResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/animes")
public class AnimeController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AnimeRepository repository;

    @Autowired
    CustomObjectMapper customObjectMapper;

    @RequestMapping(value = "", method = RequestMethod.GET, params={"n"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam("n") String str) throws IOException {
        return new JSONResponse(repository.findBySlug(str));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<AnimeModel> list() {
        return repository.find(3);
    }
}
