package net.v4lproik.googlanime.mvc.models;

/**
 * Created by joel on 07/05/2015.
 */
public class JSONResponse {

    private Object animes;

    public JSONResponse(Object animes) {
        this.animes = animes;
    }
    public Object getAnimes() {
        return animes;
    }
}
