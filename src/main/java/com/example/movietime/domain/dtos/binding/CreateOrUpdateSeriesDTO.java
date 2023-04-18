package com.example.movietime.domain.dtos.binding;

import jakarta.validation.constraints.*;

import java.util.List;

public class CreateOrUpdateSeriesDTO {
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

    @NotNull
    private Integer episodes;

    @NotEmpty
    private List<String> genres;

    @NotEmpty
    private String imageUrl;

    public CreateOrUpdateSeriesDTO() {
    }


    public String getName() {
        return name;
    }

    public CreateOrUpdateSeriesDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateOrUpdateSeriesDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getReleasedYear() {
        return releasedYear;
    }

    public CreateOrUpdateSeriesDTO setReleasedYear(Integer releasedYear) {
        this.releasedYear = releasedYear;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public CreateOrUpdateSeriesDTO setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public CreateOrUpdateSeriesDTO setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Integer getEpisodes() {
        return episodes;
    }

    public CreateOrUpdateSeriesDTO setEpisodes(Integer episodes) {
        this.episodes = episodes;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CreateOrUpdateSeriesDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public List<String> getGenres() {
        return genres;
    }

    public CreateOrUpdateSeriesDTO setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }
}
