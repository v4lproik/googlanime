package net.v4lproik.googlanime.mvc.controllers;

import net.v4lproik.googlanime.mvc.models.JSONResponse;
import net.v4lproik.googlanime.mvc.models.WebsiteFactory;
import net.v4lproik.googlanime.service.api.Website;
import net.v4lproik.googlanime.service.api.WebsiteAbstract;
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

    @Autowired
    private WebsiteFactory websiteFactory;

    @RequestMapping(value = "", method = RequestMethod.GET, params={"from"})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public JSONResponse list(@RequestParam("from") String from) throws IOException {
        WebsiteAbstract website = websiteFactory.getWebsite(from);
        website.crawl();

//        return new JSONResponse(repository.findBySlug(str));
        return null;
    }
}
