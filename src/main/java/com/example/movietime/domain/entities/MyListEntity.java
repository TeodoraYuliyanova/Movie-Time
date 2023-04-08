package com.example.movietime.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "my_list")
public class MyListEntity extends BaseEntity{


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "my_list_movies",
            joinColumns = {
                    @JoinColumn(name = "my_list_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "movie_id", referencedColumnName = "id")
            },
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"my_list_id", "movie_id"})
            }
    )
    private List<MovieEntity> movies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "my_list_series",
            joinColumns = {
                    @JoinColumn(name = "my_list_id", referencedColumnName = "id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "series_id", referencedColumnName = "id")
            },
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"my_list_id", "series_id"})
            }
    )
    private List<SeriesEntity> series;

    public MyListEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public MyListEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public MyListEntity setMovies(List<MovieEntity> movies) {
        this.movies = movies;
        return this;
    }

    public List<SeriesEntity> getSeries() {
        return series;
    }

    public MyListEntity setSeries(List<SeriesEntity> series) {
        this.series = series;
        return this;
    }
}
