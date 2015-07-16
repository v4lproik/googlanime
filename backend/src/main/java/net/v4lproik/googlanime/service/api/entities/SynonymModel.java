package net.v4lproik.googlanime.service.api.entities;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Synonym")
public class SynonymModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String title;

    public SynonymModel() {
    }

    public SynonymModel(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .toString();
    }
}
