package com.example.movietime.domain.dtos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyListDTOTest {

    private MyListDTO myListDTO;

    @BeforeEach
    public void setUp() {
        myListDTO = new MyListDTO();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        myListDTO.setId(id);
        assertEquals(id, myListDTO.getId());
    }

    @Test
    public void testSetAndGetUser() {
        UserDTO user = new UserDTO();
        myListDTO.setUser(user);
        assertEquals(user, myListDTO.getUser());
    }

    @Test
    public void testSetAndGetMovies() {
        List<MovieDTO> movies = new ArrayList<>();
        MovieDTO movie1 = new MovieDTO();
        MovieDTO movie2 = new MovieDTO();
        movies.add(movie1);
        movies.add(movie2);
        myListDTO.setMovies(movies);
        assertNotNull(myListDTO.getMovies());
        assertEquals(movies, myListDTO.getMovies());
    }

    @Test
    public void testSetAndGetSeries() {
        List<SeriesDTO> series = new ArrayList<>();
        SeriesDTO series1 = new SeriesDTO();
        SeriesDTO series2 = new SeriesDTO();
        series.add(series1);
        series.add(series2);
        myListDTO.setSeries(series);
        assertNotNull(myListDTO.getSeries());
        assertEquals(series, myListDTO.getSeries());
    }

}