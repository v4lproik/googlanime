package net.v4lproik.googlanime.mvc.controllers;


import net.v4lproik.googlanime.mvc.models.AnimeModel;
import net.v4lproik.googlanime.service.api.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/animes")
public class AnimeController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AnimeRepository repository;

    @RequestMapping(value = "/list", method = RequestMethod.GET, params={"n"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<AnimeModel> list(@RequestParam("n") String str) {
        if (str != null)
            return repository.findBySlug(str);

        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<AnimeModel> list() {
        return repository.find(3);
    }
}
