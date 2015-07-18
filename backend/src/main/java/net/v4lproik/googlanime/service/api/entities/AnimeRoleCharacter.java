package net.v4lproik.googlanime.service.api.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "Anime_has_Character")
public class AnimeRoleCharacter implements Serializable {

    @Id
    private Long idAnime;

    @Id
    private Integer idCharacter;

    @Column(name = "role")
    private String role;

    public AnimeRoleCharacter() {
    }

    public Long getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(Long idAnime) {
        this.idAnime = idAnime;
    }

    public Integer getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(Integer idCharacter) {
        this.idCharacter = idCharacter;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
