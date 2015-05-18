package net.v4lproik.googlanime.service.api;

import java.util.Arrays;

/**
 * Created by joel on 09/05/2015.
 */
public class MyAnimeListAnime {

    private Integer id;

    private String[] synonyms;

    private String englishTitle;

    private String japaneseTitle;

    private String synopsis;

    private String startedAiringDate;

    private String rank;

    private String popularity;

    private String Score;

    private String[] producers;

    private String[] genres;

    private String finishedAiringDate;

    private String youtubeVideoId;

    private String ageRating;

    private String episodeCount;

    private String episodeLength;

    private String showType;

    private String posterImage;

    private String coverImage;

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

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
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

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
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
        return Score;
    }

    public void setScore(String score) {
        Score = score;
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

    @Override
    public String toString() {
        return "MyAnimeListAnime{" +
                "id=" + id +
                ", synonyms=" + Arrays.toString(synonyms) +
                ", englishTitle='" + englishTitle + '\'' +
                ", japaneseTitle='" + japaneseTitle + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", startedAiringDate='" + startedAiringDate + '\'' +
                ", rank='" + rank + '\'' +
                ", popularity='" + popularity + '\'' +
                ", Score='" + Score + '\'' +
                ", producers=" + Arrays.toString(producers) +
                ", genres=" + Arrays.toString(genres) +
                ", finishedAiringDate='" + finishedAiringDate + '\'' +
                ", youtubeVideoId='" + youtubeVideoId + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", episodeCount='" + episodeCount + '\'' +
                ", episodeLength='" + episodeLength + '\'' +
                ", showType='" + showType + '\'' +
                ", posterImage='" + posterImage + '\'' +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}
