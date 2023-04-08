package com.example.movietime.domain.dtos.model;

import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeriesDTOTest {

    @Test
    public void testGettersAndSetters() {
        SeriesDTO seriesDTO = new SeriesDTO();

        seriesDTO.setId(1L);
        seriesDTO.setName("TestMovie");
        seriesDTO.setDescription("Description");
        seriesDTO.setReleasedYear(Year.of(2021));
        seriesDTO.setVideoURL("https://example.com/movie.mp4");
        seriesDTO.setLanguage("English");
        seriesDTO.setImageURL("https://example.com/movie.jpg");
        seriesDTO.setEpisodes(10);

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Testt");

        List<GenreDTO> genres = new ArrayList<>();
        genres.add(genreDTO);

        seriesDTO.setGenres(genres);


        assertEquals("TestMovie", seriesDTO.getName());
        assertEquals(1L, seriesDTO.getId());
        assertEquals("Description", seriesDTO.getDescription());
        assertEquals(Year.of(2021), seriesDTO.getReleasedYear());
        assertEquals("https://example.com/movie.mp4", seriesDTO.getVideoURL());
        assertEquals("English", seriesDTO.getLanguage());
        assertEquals(genres, seriesDTO.getGenres());
        assertEquals("https://example.com/movie.jpg", seriesDTO.getImageURL());
        assertEquals(10, seriesDTO.getEpisodes());


    }


}