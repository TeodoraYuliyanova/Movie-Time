package com.example.movietime.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieEntityTest {

    private MovieEntity movieEntity;
    private String name;
    private String description;
    private Year releasedYear;
    private String videoURL;
    private String language;
    private List<GenreEntity> genres;
    private String imageURL;

    @BeforeEach
    public void setUp() {
        movieEntity = new MovieEntity();
        name = "Movie Name";
        description = "Movie Description";
        releasedYear = Year.of(2022);
        videoURL = "https://example.com/movie";
        language = "English";
        genres = new ArrayList<>();
        imageURL = "https://example.com/movie/image";
    }

    @Test
    public void testGetSetName() {
        movieEntity.setName(name);
        assertEquals(name, movieEntity.getName());
    }

    @Test
    public void testGetSetDescription() {
        movieEntity.setDescription(description);
        assertEquals(description, movieEntity.getDescription());
    }

    @Test
    public void testGetSetReleasedYear() {
        movieEntity.setReleasedYear(releasedYear);
        assertEquals(releasedYear, movieEntity.getReleasedYear());
    }

    @Test
    public void testGetSetVideoURL() {
        movieEntity.setVideoURL(videoURL);
        assertEquals(videoURL, movieEntity.getVideoURL());
    }

    @Test
    public void testGetSetLanguage() {
        movieEntity.setLanguage(language);
        assertEquals(language, movieEntity.getLanguage());
    }

    @Test
    public void testGetSetGenres() {
        GenreEntity genre1 = new GenreEntity();
        GenreEntity genre2 = new GenreEntity();
        genres.add(genre1);
        genres.add(genre2);

        movieEntity.setGenres(genres);
        List<GenreEntity> retrievedGenres = movieEntity.getGenres();

        assertEquals(genres.size(), retrievedGenres.size());
        assertEquals(genres.get(0), retrievedGenres.get(0));
        assertEquals(genres.get(1), retrievedGenres.get(1));
    }

    @Test
    public void testGetSetImageURL() {
        movieEntity.setImageURL(imageURL);
        assertEquals(imageURL, movieEntity.getImageURL());
    }

}