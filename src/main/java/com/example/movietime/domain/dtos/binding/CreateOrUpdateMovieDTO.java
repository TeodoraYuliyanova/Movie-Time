package com.example.movietime.domain.dtos.binding;

import jakarta.validation.constraints.*;

import java.util.List;

public class CreateOrUpdateMovieDTO {

    @NotNull
    @Size(min = 2,max = 1000)
    private String name;

    @NotNull
    @Size(min = 10 , max = 1000000)
    private String description;

    @NotNull
    @Positive
    private Integer releasedYear;

    @NotEmpty
    private String videoURL;

    @NotEmpty
    @Size(min = 5,max = 1000)
    private String language;

    @NotEmpty
    private List<String> genres;

    @NotBlank
    private String imageUrl;

    public CreateOrUpdateMovieDTO() {
    }

    public String getName() {
        return name;
    }

    public CreateOrUpdateMovieDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOrUpdateMovieDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getReleasedYear() {
        return releasedYear;
    }

    public CreateOrUpdateMovieDTO setReleasedYear(Integer releasedYear) {
        this.releasedYear = releasedYear;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public CreateOrUpdateMovieDTO setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public CreateOrUpdateMovieDTO setLanguage(String language) {
        this.language = language;
        return this;
    }

    public List<String> getGenres() {
        return genres;
    }

    public CreateOrUpdateMovieDTO setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateOrUpdateMovieDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
