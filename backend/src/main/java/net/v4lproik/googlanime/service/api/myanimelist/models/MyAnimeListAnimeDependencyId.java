package net.v4lproik.googlanime.service.api.myanimelist.models;

public class MyAnimeListAnimeDependencyId {

    private int id;

    private String englishTitle;

    private String type;

    public MyAnimeListAnimeDependencyId() {
    }

    public MyAnimeListAnimeDependencyId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public void setEnglishTitle(String englishTitle) {
        this.englishTitle = englishTitle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
