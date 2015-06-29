package net.v4lproik.googlanime.service.api.models;

import javax.persistence.*;

@Entity
@Table(name = "Genre")
public class GenreModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GenreModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}