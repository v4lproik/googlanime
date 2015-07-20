package net.v4lproik.googlanime.service.api.myanimelist.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class MyAnimeListMangaDependency extends MyAnimeListEntryDependency{

    private Integer nbVolumes;

    private Integer mbChapters;

    private String serialization;

    public MyAnimeListMangaDependency(Integer nbVolumes, Integer mbChapters, String serialization) {
        this.nbVolumes = nbVolumes;
        this.mbChapters = mbChapters;
        this.serialization = serialization;
    }

    public MyAnimeListMangaDependency(Integer id) {
        super(id);
    }

    public Integer getNbVolumes() {
        return nbVolumes;
    }

    public void setNbVolumes(Integer nbVolumes) {
        this.nbVolumes = nbVolumes;
    }

    public Integer getMbChapters() {
        return mbChapters;
    }

    public void setMbChapters(Integer mbChapters) {
        this.mbChapters = mbChapters;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("nbVolumes", nbVolumes)
                .append("mbChapters", mbChapters)
                .append("serialization", serialization)
                .toString() + super.toString();
    }

    @Override
    public boolean isAnime() {
        return false;
    }

    @Override
    public boolean isManga() {
        return true;
    }
}
