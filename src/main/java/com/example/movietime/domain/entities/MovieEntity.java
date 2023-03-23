package com.example.movietime.domain.entities;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;

@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "released_year",nullable = false)
    private Year releasedYear;

    @Column(nullable = false)
    private String cast;

    @Column(name = "video_url",nullable = false)
    private String videoURL;

    @Column(nullable = false)
    private String language;

    @OneToOne
    private PictureEntity picture;

    @OneToMany
    private List<GenreEntity> genres;

    public MovieEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Year getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(Year releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public void setPicture(PictureEntity picture) {
        this.picture = picture;
    }
}
