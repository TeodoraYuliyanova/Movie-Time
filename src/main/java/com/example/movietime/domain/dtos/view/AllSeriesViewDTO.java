package com.example.movietime.domain.dtos.view;

public class AllSeriesViewDTO {

    private Long id;
    private String name;
    private String description;
    private String releasedYear;
    private String videoURL;
    private String language;
    private Integer episodes;
    private String imageURL;


    public AllSeriesViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public AllSeriesViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AllSeriesViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AllSeriesViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public AllSeriesViewDTO setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public AllSeriesViewDTO setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public AllSeriesViewDTO setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public AllSeriesViewDTO setEpisodes(Integer episodes) {
        this.episodes = episodes;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public AllSeriesViewDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}
