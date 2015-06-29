package net.v4lproik.googlanime.service.api.models;

import javax.persistence.*;

@Entity
@Table(name="Tag")
public class TagModel {

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

    public TagModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
