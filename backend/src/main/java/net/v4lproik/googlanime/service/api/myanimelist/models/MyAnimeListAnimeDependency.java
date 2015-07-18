package net.v4lproik.googlanime.service.api.myanimelist.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class MyAnimeListAnimeDependency {
    private Integer id;

    private String type;

    private String title;

    private String[] synonyms;

    private String englishTitle;

    private String japaneseTitle;

    private String synopsis;

    private String startedAiringDate;

    private String rank;

    private String popularity;

    private String score;

    private String[] producers;

    private String[] genres;

    private String finishedAiringDate;

    private String ageRating;

    private String episodeCount;

    private String episodeLength;

    private String showType;

    private String posterImage;

    private MyAnimeListAnimeDependency parent;

    private List<MyAnimeListAuthor> authors = new ArrayList<MyAnimeListAuthor>();

    private String[] tags;

    private List<MyAnimeListAnimeDependencyId> sequels = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListAnimeDependencyId> alternativeVersions = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListAnimeDependencyId> prequels = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListAnimeDependencyId> spinoff = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListAnimeDependencyId> sideStories = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListAnimeDependencyId> others = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListAnimeDependencyId> summaries = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListAnimeDependencyId> adaptations = new ArrayList<MyAnimeListAnimeDependencyId>();

    private List<MyAnimeListCharacter> characters = new ArrayList<MyAnimeListCharacter>();

    public MyAnimeListAnimeDependency(Integer id) {
        this.id = id;
    }

    public MyAnimeListAnimeDependency() {
    }

    public MyAnimeListAnimeDependency(Integer id, String type, String title, String[] synonyms, String englishTitle, String japaneseTitle, String synopsis, String startedAiringDate, String rank, String popularity, String score, String[] producers, String[] genres, String finishedAiringDate, String ageRating, String episodeCount, String episodeLength, String showType, String posterImage, MyAnimeListAnimeDependency parent, List<MyAnimeListAuthor> authors, String[] tags, List<MyAnimeListAnimeDependencyId> sequels, List<MyAnimeListAnimeDependencyId> alternativeVersions, List<MyAnimeListAnimeDependencyId> prequels, List<MyAnimeListAnimeDependencyId> spinoff, List<MyAnimeListAnimeDependencyId> sideStories, List<MyAnimeListAnimeDependencyId> others, List<MyAnimeListAnimeDependencyId> summaries, List<MyAnimeListAnimeDependencyId> adaptations, List<MyAnimeListCharacter> characters) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.synonyms = synonyms;
        this.englishTitle = englishTitle;
        this.japaneseTitle = japaneseTitle;
        this.synopsis = synopsis;
        this.startedAiringDate = startedAiringDate;
        this.rank = rank;
        this.popularity = popularity;
        this.score = score;
        this.producers = producers;
        this.genres = genres;
        this.finishedAiringDate = finishedAiringDate;
        this.ageRating = ageRating;
        this.episodeCount = episodeCount;
        this.episodeLength = episodeLength;
        this.showType = showType;
        this.posterImage = posterImage;
        this.parent = parent;
        this.authors = authors;
        this.tags = tags;
        this.sequels = sequels;
        this.alternativeVersions = alternativeVersions;
        this.prequels = prequels;
        this.spinoff = spinoff;
        this.sideStories = sideStories;
        this.others = others;
        this.summaries = summaries;
        this.adaptations = adaptations;
        this.characters = characters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getJapaneseTitle() {
        return japaneseTitle;
    }

    public void setJapaneseTitle(String japaneseTitle) {
        this.japaneseTitle = japaneseTitle;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getStartedAiringDate() {
        return startedAiringDate;
    }

    public void setStartedAiringDate(String startedAiringDate) {
        this.startedAiringDate = startedAiringDate;
    }

    public String getFinishedAiringDate() {
        return finishedAiringDate;
    }

    public void setFinishedAiringDate(String finishedAiringDate) {
        this.finishedAiringDate = finishedAiringDate;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
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

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String[] getProducers() {
        return producers;
    }

    public void setProducers(String[] producers) {
        this.producers = producers;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String[] synonyms) {
        this.synonyms = synonyms;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public List<MyAnimeListCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<MyAnimeListCharacter> characters) {
        this.characters = characters;
    }

    public List<MyAnimeListAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<MyAnimeListAuthor> authors) {
        this.authors = authors;
    }

    public List<MyAnimeListAnimeDependencyId> getSequels() {
        return sequels;
    }

    public void setSequels(List<MyAnimeListAnimeDependencyId> sequels) {
        this.sequels = sequels;
    }

    public List<MyAnimeListAnimeDependencyId> getAlternativeVersions() {
        return alternativeVersions;
    }

    public void setAlternativeVersions(List<MyAnimeListAnimeDependencyId> alternativeVersions) {
        this.alternativeVersions = alternativeVersions;
    }

    public List<MyAnimeListAnimeDependencyId> getPrequels() {
        return prequels;
    }

    public void setPrequels(List<MyAnimeListAnimeDependencyId> prequels) {
        this.prequels = prequels;
    }

    public List<MyAnimeListAnimeDependencyId> getSpinoff() {
        return spinoff;
    }

    public void setSpinoff(List<MyAnimeListAnimeDependencyId> spinoff) {
        this.spinoff = spinoff;
    }

    public List<MyAnimeListAnimeDependencyId> getSideStories() {
        return sideStories;
    }

    public void setSideStories(List<MyAnimeListAnimeDependencyId> sideStories) {
        this.sideStories = sideStories;
    }

    public List<MyAnimeListAnimeDependencyId> getOthers() {
        return others;
    }

    public void setOthers(List<MyAnimeListAnimeDependencyId> others) {
        this.others = others;
    }

    public List<MyAnimeListAnimeDependencyId> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<MyAnimeListAnimeDependencyId> summaries) {
        this.summaries = summaries;
    }

    public List<MyAnimeListAnimeDependencyId> getAdaptations() {
        return adaptations;
    }

    public void setAdaptations(List<MyAnimeListAnimeDependencyId> adaptations) {
        this.adaptations = adaptations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MyAnimeListAnimeDependency getParent() {
        return parent;
    }

    public void setParent(MyAnimeListAnimeDependency parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MyAnimeListAnimeDependency that = (MyAnimeListAnimeDependency) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", id)
                .append("type", type)
                .append("title", title)
                .append("synonyms", synonyms)
                .append("englishTitle", englishTitle)
                .append("japaneseTitle", japaneseTitle)
                .append("synopsis", synopsis)
                .append("startedAiringDate", startedAiringDate)
                .append("rank", rank)
                .append("popularity", popularity)
                .append("score", score)
                .append("producers", producers)
                .append("genres", genres)
                .append("finishedAiringDate", finishedAiringDate)
                .append("ageRating", ageRating)
                .append("episodeCount", episodeCount)
                .append("episodeLength", episodeLength)
                .append("showType", showType)
                .append("posterImage", posterImage)
                .append("authors", authors)
                .append("tags", tags)
                .append("sequels", sequels)
                .append("alternativeVersions", alternativeVersions)
                .append("prequels", prequels)
                .append("spinoff", spinoff)
                .append("sideStories", sideStories)
                .append("others", others)
                .append("summaries", summaries)
                .append("adaptations", adaptations)
                .append("characters", characters)
                .toString();
    }
}
