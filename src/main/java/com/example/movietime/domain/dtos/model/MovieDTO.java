package com.example.movietime.domain.dtos.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Year;
import java.util.List;

public class MovieDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2,max = 1000)
    private String name;

    @NotNull
    @Size(min = 10 , max = 1000000)
    private String description;

    @NotNull
    private Year releasedYear;

    @NotNull
    private String videoURL;

    @NotNull
    @Size(min = 5,max = 1000)
    private String language;

    private List<GenreDTO> genres;

    @NotNull
    private String imageURL;

    public MovieDTO() {
    }

    public Long getId() {
        return id;
    }

    public MovieDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MovieDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MovieDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Year getReleasedYear() {
        return releasedYear;
    }

    public MovieDTO setReleasedYear(Year releasedYear) {
        this.releasedYear = releasedYear;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public MovieDTO setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public MovieDTO setLanguage(String language) {
        this.language = language;
        return this;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public MovieDTO setGenres(List<GenreDTO> genres) {
        this.genres = genres;
        return this;
    }

    public String getImageURL() {
        return imageURL;
    }

    public MovieDTO setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}
