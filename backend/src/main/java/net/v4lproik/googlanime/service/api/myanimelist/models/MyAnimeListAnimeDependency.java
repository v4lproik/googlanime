package net.v4lproik.googlanime.service.api.myanimelist.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class MyAnimeListAnimeDependency extends MyAnimeListEntryDependency{

    private String[] producers;

    private String episodeCount;

    private String episodeLength;

    private String showType;

    public MyAnimeListAnimeDependency(Integer id) {
        super(id);
    }

    public MyAnimeListAnimeDependency() {
    }

    public String[] getProducers() {
        return producers;
    }

    public void setProducers(String[] producers) {
        this.producers = producers;
    }

    public String getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(String episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getEpisodeLength() {
        return episodeLength;
    }

    public void setEpisodeLength(String episodeLength) {
        this.episodeLength = episodeLength;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("producers", producers)
                .append("episodeCount", episodeCount)
                .append("episodeLength", episodeLength)
                .append("showType", showType)
                .toString();
    }

    @Override
    public boolean isAnime() {
        return true;
    }

    @Override
    public boolean isManga() {
        return false;
    }


}
