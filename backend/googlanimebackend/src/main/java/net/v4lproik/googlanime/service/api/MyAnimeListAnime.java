package net.v4lproik.googlanime.service.api;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 09/05/2015.
 */
public class MyAnimeListAnime {

    private Integer id;

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

    private String[] authors;

    private String[] tags;

    private List<Object> sequels = new ArrayList<Object>();

    private List<Object> alternativeVersions = new ArrayList<Object>();

    private List<Object> prequels = new ArrayList<Object>();

    private List<Object> spinoff = new ArrayList<Object>();

    private List<Object> sideStories = new ArrayList<Object>();

    private List<Object> others = new ArrayList<Object>();

    private List<MyAnimeListCharacter> characters = new ArrayList<MyAnimeListCharacter>();

    public MyAnimeListAnime(Integer id) {
        this.id = id;
    }

    public MyAnimeListAnime() {
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

    public List<Object> getSequels() {
        return sequels;
    }

    public void setSequels(List<Object> sequels) {
        this.sequels = sequels;
    }

    public List<Object> getPrequels() {
        return prequels;
    }

    public void setPrequels(List<Object> prequels) {
        this.prequels = prequels;
    }

    public List<Object> getSpinoff() {
        return spinoff;
    }

    public void setSpinoff(List<Object> spinoff) {
        this.spinoff = spinoff;
    }

    public List<Object> getSideStories() {
        return sideStories;
    }

    public void setSideStories(List<Object> sideStories) {
        this.sideStories = sideStories;
    }

    public List<Object> getOthers() {
        return others;
    }

    public void setOthers(List<Object> others) {
        this.others = others;
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

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("synonyms", synonyms)
                .add("englishTitle", englishTitle)
                .add("japaneseTitle", japaneseTitle)
                .add("synopsis", synopsis)
                .add("startedAiringDate", startedAiringDate)
                .add("rank", rank)
                .add("popularity", popularity)
                .add("score", score)
                .add("producers", producers)
                .add("genres", genres)
                .add("finishedAiringDate", finishedAiringDate)
                .add("ageRating", ageRating)
                .add("episodeCount", episodeCount)
                .add("episodeLength", episodeLength)
                .add("showType", showType)
                .add("posterImage", posterImage)
                .add("authors", authors)
                .add("tags", tags)
                .add("sequels", sequels)
                .add("alternativeVersions", alternativeVersions)
                .add("prequels", prequels)
                .add("spinoff", spinoff)
                .add("sideStories", sideStories)
                .add("others", others)
                .add("characters", characters)
                .toString();
    }

    public List<MyAnimeListCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<MyAnimeListCharacter> characters) {
        this.characters = characters;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public List<Object> getAlternativeVersions() {
        return alternativeVersions;
    }

    public void setAlternativeVersions(List<Object> alternativeVersions) {
        this.alternativeVersions = alternativeVersions;
    }

}
