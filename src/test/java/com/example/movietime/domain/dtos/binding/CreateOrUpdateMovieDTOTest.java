package com.example.movietime.domain.dtos.binding;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrUpdateMovieDTOTest {

    @Test
    public void testGettersAndSetters() {
        CreateOrUpdateMovieDTO createOrUpdateMovieDTO = new CreateOrUpdateMovieDTO();

        createOrUpdateMovieDTO.setName("TestMovie");
        createOrUpdateMovieDTO.setDescription("Description");
        createOrUpdateMovieDTO.setReleasedYear(2021);
        createOrUpdateMovieDTO.setVideoURL("https://example.com/movie.mp4");
        createOrUpdateMovieDTO.setLanguage("English");

        List<String> genres = new ArrayList<>();
        genres.add("Action");
        genres.add("Thriller");
        createOrUpdateMovieDTO.setGenres(genres);
        createOrUpdateMovieDTO.setImageUrl("https://example.com/movie.jpg");


        assertEquals("TestMovie", createOrUpdateMovieDTO.getName());
        assertEquals("Description", createOrUpdateMovieDTO.getDescription());
        assertEquals(2021, createOrUpdateMovieDTO.getReleasedYear());
        assertEquals("https://example.com/movie.mp4", createOrUpdateMovieDTO.getVideoURL());
        assertEquals("English", createOrUpdateMovieDTO.getLanguage());
        assertEquals(genres, createOrUpdateMovieDTO.getGenres());
        assertEquals("https://example.com/movie.jpg", createOrUpdateMovieDTO.getImageUrl());

    }
}