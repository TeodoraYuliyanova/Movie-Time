package com.example.movietime.domain.dtos.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllMoviesViewDTOTest {

    private AllMoviesViewDTO allMoviesViewDTO;

    @BeforeEach
    public void setUp() {
        allMoviesViewDTO = new AllMoviesViewDTO();
    }

    @Test
    public void testGetSetId() {
        Long id = 1L;
        allMoviesViewDTO.setId(id);
        assertEquals(id, allMoviesViewDTO.getId());
    }

    @Test
    public void testGetSetName() {
        String name = "Example Movie";
        allMoviesViewDTO.setName(name);
        assertEquals(name, allMoviesViewDTO.getName());
    }

    @Test
    public void testGetSetDescription() {
        String description = "This is an example movie";
        allMoviesViewDTO.setDescription(description);
        assertEquals(description, allMoviesViewDTO.getDescription());
    }

    @Test
    public void testGetSetReleasedYear() {
        String releasedYear = "2021";
        allMoviesViewDTO.setReleasedYear(releasedYear);
        assertEquals(releasedYear, allMoviesViewDTO.getReleasedYear());
    }

    @Test
    public void testGetSetVideoURL() {
        String videoURL = "https://example.com/movie.mp4";
        allMoviesViewDTO.setVideoURL(videoURL);
        assertEquals(videoURL, allMoviesViewDTO.getVideoURL());
    }

    @Test
    public void testGetSetLanguage() {
        String language = "English";
        allMoviesViewDTO.setLanguage(language);
        assertEquals(language, allMoviesViewDTO.getLanguage());
    }

    @Test
    public void testGetSetImageURL() {
        String imageURL = "https://example.com/movie.jpg";
        allMoviesViewDTO.setImageURL(imageURL);
        assertEquals(imageURL, allMoviesViewDTO.getImageURL());
    }

}