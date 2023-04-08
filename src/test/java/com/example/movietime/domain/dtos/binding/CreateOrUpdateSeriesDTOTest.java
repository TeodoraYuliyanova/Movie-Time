package com.example.movietime.domain.dtos.binding;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrUpdateSeriesDTOTest {

    @Test
    public void testGettersAndSetters() {
        CreateOrUpdateSeriesDTO createOrUpdateSeriesDTO = new CreateOrUpdateSeriesDTO();

        createOrUpdateSeriesDTO.setName("TestMovie");
        createOrUpdateSeriesDTO.setDescription("Description");
        createOrUpdateSeriesDTO.setReleasedYear(2021);
        createOrUpdateSeriesDTO.setVideoURL("https://example.com/movie.mp4");
        createOrUpdateSeriesDTO.setLanguage("English");
        createOrUpdateSeriesDTO.setEpisodes(10);

        List<String> genres = new ArrayList<>();
        genres.add("Action");
        genres.add("Thriller");
        createOrUpdateSeriesDTO.setGenres(genres);
        createOrUpdateSeriesDTO.setImageUrl("https://example.com/movie.jpg");


        assertEquals("TestMovie", createOrUpdateSeriesDTO.getName());
        assertEquals("Description", createOrUpdateSeriesDTO.getDescription());
        assertEquals(2021, createOrUpdateSeriesDTO.getReleasedYear());
        assertEquals("https://example.com/movie.mp4", createOrUpdateSeriesDTO.getVideoURL());
        assertEquals("English", createOrUpdateSeriesDTO.getLanguage());
        assertEquals(genres, createOrUpdateSeriesDTO.getGenres());
        assertEquals("https://example.com/movie.jpg", createOrUpdateSeriesDTO.getImageUrl());
        assertEquals(10, createOrUpdateSeriesDTO.getEpisodes());

    }

}