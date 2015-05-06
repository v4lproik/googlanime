package net.v4lproik.googlanime.mvc.models;
/**
 * Created by joel on 30/04/2015.
 */

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AnimeModel {

    @Id
    private Integer id;

    private String slug;

    public AnimeModel(Integer id) {
        this.id = id;
    }
}
