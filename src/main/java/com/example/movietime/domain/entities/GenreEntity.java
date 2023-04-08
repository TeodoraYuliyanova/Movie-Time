package com.example.movietime.domain.entities;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "genres")
public class GenreEntity extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<SeriesEntity> series;

    @ManyToMany(mappedBy = "genres")
    private List<MovieEntity> movies;

    public GenreEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeriesEntity> getSeries() {
        return Collections.unmodifiableList(series);
    }

    public GenreEntity setSeries(List<SeriesEntity> series) {
        this.series = series;
        return this;
    }

    public List<MovieEntity> getMovies() {
        return Collections.unmodifiableList(movies);
    }

    public GenreEntity setMovies(List<MovieEntity> movies) {
        this.movies = movies;
        return this;
    }
}
