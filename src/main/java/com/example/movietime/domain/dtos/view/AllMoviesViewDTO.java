package com.example.movietime.domain.dtos.view;

public class AllMoviesViewDTO {

    private Long id;
    private String name;
    private String description;
    private String releasedYear;
    private String videoURL;
    private String language;
    private String imageURL;

    public AllMoviesViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public AllMoviesViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AllMoviesViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AllMoviesViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getReleasedYear() {
        return releasedYear;
    }

    public AllMoviesViewDTO setReleasedYear(String releasedYear) {
        this.releasedYear = releasedYear;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public AllMoviesViewDTO setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public AllMoviesViewDTO setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public AllMoviesViewDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}
