package com.example.movietime.domain.dtos.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllSeriesViewDTOTest {

    private AllSeriesViewDTO allSeriesViewDTO;

    @BeforeEach
    public void setUp() {
        allSeriesViewDTO = new AllSeriesViewDTO();
    }

    @Test
    public void testGetSetId() {
        Long id = 1L;
        allSeriesViewDTO.setId(id);
        assertEquals(id, allSeriesViewDTO.getId());
    }

    @Test
    public void testGetSetName() {
        String name = "Example Series";
        allSeriesViewDTO.setName(name);
        assertEquals(name, allSeriesViewDTO.getName());
    }

    @Test
    public void testGetSetDescription() {
        String description = "This is an example series";
        allSeriesViewDTO.setDescription(description);
        assertEquals(description, allSeriesViewDTO.getDescription());
    }

    @Test
    public void testGetSetReleasedYear() {
        String releasedYear = "2021";
        allSeriesViewDTO.setReleasedYear(releasedYear);
        assertEquals(releasedYear, allSeriesViewDTO.getReleasedYear());
    }

    @Test
    public void testGetSetVideoURL() {
        String videoURL = "https://example.com/series.mp4";
        allSeriesViewDTO.setVideoURL(videoURL);
        assertEquals(videoURL, allSeriesViewDTO.getVideoURL());
    }

    @Test
    public void testGetSetLanguage() {
        String language = "English";
        allSeriesViewDTO.setLanguage(language);
        assertEquals(language, allSeriesViewDTO.getLanguage());
    }

    @Test
    public void testGetSetEpisodes() {
        Integer episodes = 10;
        allSeriesViewDTO.setEpisodes(episodes);
        assertEquals(episodes, allSeriesViewDTO.getEpisodes());
    }

    @Test
    public void testGetSetImageURL() {
        String imageURL = "https://example.com/series.jpg";
        allSeriesViewDTO.setImageURL(imageURL);
        assertEquals(imageURL, allSeriesViewDTO.getImageURL());
    }

}