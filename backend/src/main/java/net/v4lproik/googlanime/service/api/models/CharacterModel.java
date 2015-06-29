package net.v4lproik.googlanime.service.api.models;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Character")
public class CharacterModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String firstName;

    private String lastName;

    private String japaneseName;

    private String role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public void setJapaneseName(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public CharacterModel() {
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("japaneseName", japaneseName)
                .add("role", role)
                .toString();
    }
}
