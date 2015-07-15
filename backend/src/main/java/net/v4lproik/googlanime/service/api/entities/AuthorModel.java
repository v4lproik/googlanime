package net.v4lproik.googlanime.service.api.entities;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "Author")
public class AuthorModel {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String firstName;

    private String lastName;

    private String[] job;

    public AuthorModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String[] getJob() {
        return job;
    }

    public void setJob(String[] job) {
        this.job = job;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("job", job)
                .toString();
    }
}
