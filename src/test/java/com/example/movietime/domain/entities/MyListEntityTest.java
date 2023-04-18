package com.example.movietime.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyListEntityTest {

    private MyListEntity myListEntity;

    @BeforeEach
    public void setUp() {
        myListEntity = new MyListEntity();
    }

    @Test
    public void testSetAndGetUser() {
        UserEntity user = new UserEntity();
        myListEntity.setUser(user);
        assertEquals(user, myListEntity.getUser());
    }

    @Test
    public void testSetAndGetMovies() {
        List<MovieEntity> movies = new ArrayList<>();
        MovieEntity movie1 = new MovieEntity();
        MovieEntity movie2 = new MovieEntity();
        movies.add(movie1);
        movies.add(movie2);
        myListEntity.setMovies(movies);
        assertNotNull(myListEntity.getMovies());
        assertEquals(movies, myListEntity.getMovies());
    }

    @Test
    public void testSetAndGetSeries() {
        List<SeriesEntity> series = new ArrayList<>();
        SeriesEntity series1 = new SeriesEntity();
        SeriesEntity series2 = new SeriesEntity();
        series.add(series1);
        series.add(series2);
        myListEntity.setSeries(series);
        assertNotNull(myListEntity.getSeries());
        assertEquals(series, myListEntity.getSeries());
    }

}