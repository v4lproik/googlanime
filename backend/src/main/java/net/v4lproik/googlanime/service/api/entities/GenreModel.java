package net.v4lproik.googlanime.service.api.entities;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Genre")
public class GenreModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String name;

    public GenreModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreModel that = (GenreModel) o;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, name);
    }
}