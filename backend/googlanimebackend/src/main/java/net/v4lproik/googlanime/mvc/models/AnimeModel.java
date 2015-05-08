package net.v4lproik.googlanime.mvc.models;
/**
 * Created by joel on 30/04/2015.
 */


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;


@Entity
public class AnimeModel {

    @Id
    private Integer id;

    private String slug;

    private String canonicalTitle;

    private String englishTitle;

    private String romajiTitle;

    private String japaneseTitle;

    private String synopsis;

    private String startedAiringDate;

    private String finishedAiringDate;

    private String youtubeVideoId;

    private String ageRating;

    private String episodeCount;

    private String episodeLength;

    private String showType;

    private String posterImage;

    private String coverImage;

    private String communityRating;

    private List<String> genres;

    private Map<String, String> links;

//    private List<Map<String, String>> titles;

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

    public String getCommunityRating() {
        return communityRating;
    }

    public void setCommunityRating(String communityRating) {
        this.communityRating = communityRating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getRomajiTitle() {
        return romajiTitle;
    }

    public void setRomajiTitle(String romajiTitle) {
        this.romajiTitle = romajiTitle;
    }

    public String getJapaneseTitle() {
        return japaneseTitle;
    }

    public void setJapaneseTitle(String japaneseTitle) {
        this.japaneseTitle = japaneseTitle;
    }

    public AnimeModel(Integer id) {
        this.id = id;
    }

}
