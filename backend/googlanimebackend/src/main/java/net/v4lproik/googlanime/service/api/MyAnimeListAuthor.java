package net.v4lproik.googlanime.service.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by joel on 31/05/2015.
 */
public class MyAnimeListAuthor {

    private Integer id;

    private String firstName;

    private String lastName;

    private String[] job;

    public MyAnimeListAuthor() {
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
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("job", job)
                .toString();
    }
}
