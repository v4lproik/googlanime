package net.v4lproik.googlanime.service.api.entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Anime")
public class AnimeModel {

    @Id
    @Column(name = "id")
    private Long id;

    private String type;

    private String title;

    @OneToMany
    @JoinColumn(name = "id")
    private List<SynonymModel> synonyms;

    private String englishTitle;

    private String japaneseTitle;

    private String synopsis;

    private String startedAiringDate;

    private String rank;

    private String popularity;

    private String score;

    private String finishedAiringDate;

    private String ageRating;

    private String episodeCount;

    private String episodeLength;

    private String showType;

    private String posterImage;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Anime_has_Producer",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idProducer", referencedColumnName="id")
            })
    private List<ProducerModel> producers;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Anime_has_Genre",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idGenre", referencedColumnName="id")
            })
    private List<GenreModel> genres;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Anime_has_Author",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idAuthor", referencedColumnName="id")
            })    private List<AuthorModel> authors;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Anime_has_Tag",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idTag", referencedColumnName="id")
            })
    private List<TagModel> tags;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Sequels",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idSequel", referencedColumnName="id")
            })
    private List<AnimeModel> sequels;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Alternatives",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idAlternative", referencedColumnName="id")
            })
    private List<AnimeModel> alternativeVersions;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Prequels",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idPrequel", referencedColumnName="id")
            })
    private List<AnimeModel> prequels;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="SpinOffs",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idSpinOff", referencedColumnName="id")
            })
    private List<AnimeModel> spinoff;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="SideStories",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idSideStory", referencedColumnName="id")
            })
    private List<AnimeModel> sideStories;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Others",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idOther", referencedColumnName="id")
            })
    private List<AnimeModel> others;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Summaries",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idSummary", referencedColumnName="id")
            })
    private List<AnimeModel> summaries;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Adaptations",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idAdaptation", referencedColumnName="id")
            })
    private List<AnimeModel> adaptations;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="Anime_has_Character",
            joinColumns={@JoinColumn(name="idAnime", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="idCharacter", referencedColumnName="id")
            })
    private List<CharacterModel> characters;

    public AnimeModel(Long id) {
        this.id = id;
    }

    public AnimeModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CharacterModel> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterModel> characters) {
        this.characters = characters;
    }

    public List<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorModel> authors) {
        this.authors = authors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AnimeModel> getSequels() {
        return sequels;
    }

    public void setSequels(List<AnimeModel> sequels) {
        this.sequels = sequels;
    }

    public List<AnimeModel> getAlternativeVersions() {
        return alternativeVersions;
    }

    public void setAlternativeVersions(List<AnimeModel> alternativeVersions) {
        this.alternativeVersions = alternativeVersions;
    }

    public List<AnimeModel> getPrequels() {
        return prequels;
    }

    public void setPrequels(List<AnimeModel> prequels) {
        this.prequels = prequels;
    }

    public List<AnimeModel> getSpinoff() {
        return spinoff;
    }

    public void setSpinoff(List<AnimeModel> spinoff) {
        this.spinoff = spinoff;
    }

    public List<AnimeModel> getSideStories() {
        return sideStories;
    }

    public void setSideStories(List<AnimeModel> sideStories) {
        this.sideStories = sideStories;
    }

    public List<AnimeModel> getOthers() {
        return others;
    }

    public void setOthers(List<AnimeModel> others) {
        this.others = others;
    }

    public List<AnimeModel> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<AnimeModel> summaries) {
        this.summaries = summaries;
    }

    public List<AnimeModel> getAdaptations() {
        return adaptations;
    }

    public void setAdaptations(List<AnimeModel> adaptations) {
        this.adaptations = adaptations;
    }

    public List<SynonymModel> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<SynonymModel> synonyms) {
        this.synonyms = synonyms;
    }

    public List<ProducerModel> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducerModel> producers) {
        this.producers = producers;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
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