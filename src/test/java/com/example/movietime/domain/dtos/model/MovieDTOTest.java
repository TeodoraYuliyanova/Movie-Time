package com.example.movietime.domain.dtos.model;

import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieDTOTest {

    @Test
    public void testGettersAndSetters() {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setId(1L);
        movieDTO.setName("TestMovie");
        movieDTO.setDescription("Description");
        movieDTO.setReleasedYear(Year.of(2021));
        movieDTO.setVideoURL("https://example.com/movie.mp4");
        movieDTO.setLanguage("English");
        movieDTO.setImageURL("https://example.com/movie.jpg");

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Testt");

        List<GenreDTO> genres = new ArrayList<>();
        genres.add(genreDTO);

        movieDTO.setGenres(genres);


        assertEquals("TestMovie", movieDTO.getName());
        assertEquals(1L, movieDTO.getId());
        assertEquals("Description", movieDTO.getDescription());
        assertEquals(Year.of(2021), movieDTO.getReleasedYear());
        assertEquals("https://example.com/movie.mp4", movieDTO.getVideoURL());
        assertEquals("English", movieDTO.getLanguage());
        assertEquals(genres, movieDTO.getGenres());
        assertEquals("https://example.com/movie.jpg", movieDTO.getImageURL());


    }

}