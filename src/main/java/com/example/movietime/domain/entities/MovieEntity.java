package com.example.movietime.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.Year;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity{

    @Column(nullable = false)
    @Size(min = 2,max = 1000)
    private String name;

    @Column(nullable = false)
    @Size(min = 10 , max = 1000000)
    private String description;

    @Column(name = "released_year",nullable = false)
    private Year releasedYear;
    @Column(name = "video_url",nullable = false)
    private String videoURL;

    @Column(nullable = false)
    @Size(min = 5,max = 1000)
    private String language;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(
            name = "movies_genres",
            joinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id", referencedColumnName = "id")
            },
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"movie_id", "genre_id"})
            }
    )
    private List<GenreEntity> genres;

    @Column(name = "image_url",nullable = false)
    private String imageURL;

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

    public String getImageURL() {
        return imageURL;
    }

    public MovieEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }
}
