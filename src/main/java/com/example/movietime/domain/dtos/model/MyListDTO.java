package com.example.movietime.domain.dtos.model;

import com.example.movietime.domain.entities.MovieEntity;
import com.example.movietime.domain.entities.SeriesEntity;
import com.example.movietime.domain.entities.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class MyListDTO {

    @NotNull
    private Long id;
    @NotNull
    private UserDTO user;
    private List<MovieDTO> movies;
    private List<SeriesDTO> series;

    public MyListDTO() {
    }

    public Long getId() {
        return id;
    }

    public MyListDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTO getUser() {
        return user;
    }

    public MyListDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public MyListDTO setMovies(List<MovieDTO> movies) {
        this.movies = movies;
        return this;
    }

    public List<SeriesDTO> getSeries() {
        return series;
    }

    public MyListDTO setSeries(List<SeriesDTO> series) {
        this.series = series;
        return this;
    }
}
