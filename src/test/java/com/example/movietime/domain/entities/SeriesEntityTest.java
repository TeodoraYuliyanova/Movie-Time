package com.example.movietime.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;


import static org.junit.jupiter.api.Assertions.*;

class SeriesEntityTest {

    private SeriesEntity seriesEntity;

    @BeforeEach
    public void setUp() {
        seriesEntity = new SeriesEntity();
    }

    @Test
    public void testSetNameAndGetName() {
        String name = "Test Series Name";
        seriesEntity.setName(name);
        assertEquals(name, seriesEntity.getName());
    }

    @Test
    public void testSetDescriptionAndGetDescription() {
        String description = "Test series description";
        seriesEntity.setDescription(description);
        assertEquals(description, seriesEntity.getDescription());
    }

    @Test
    public void testSetReleasedYearAndGetReleasedYear() {
        Year releasedYear = Year.of(2020);
        seriesEntity.setReleasedYear(releasedYear);
        assertEquals(releasedYear, seriesEntity.getReleasedYear());
    }

    @Test
    public void testSetVideoURLAndGetVideoURL() {
        String videoURL = "https://example.com/video";
        seriesEntity.setVideoURL(videoURL);
        assertEquals(videoURL, seriesEntity.getVideoURL());
    }

    @Test
    public void testSetLanguageAndGetLanguage() {
        String language = "English";
        seriesEntity.setLanguage(language);
        assertEquals(language, seriesEntity.getLanguage());
    }

    @Test
    public void testSetEpisodesAndGetEpisodes() {
        Integer episodes = 10;
        seriesEntity.setEpisodes(episodes);
        assertEquals(episodes, seriesEntity.getEpisodes());
    }


    @Test
    public void testSetImageURLAndGetImageURL() {
        String imageURL = "https://example.com/image";
        seriesEntity.setImageURL(imageURL);
        assertEquals(imageURL, seriesEntity.getImageURL());
    }
}