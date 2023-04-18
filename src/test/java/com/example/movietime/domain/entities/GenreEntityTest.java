package com.example.movietime.domain.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GenreEntityTest {


    @InjectMocks
    private GenreEntity genreEntity;

    @Mock
    private SeriesEntity seriesEntityMock;

    @Mock
    private MovieEntity movieEntityMock;

    @Test
    public void testGetName() {
        String expectedName = "Action";
        genreEntity.setName(expectedName);

        String actualName = genreEntity.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetName() {
        String expectedName = "Comedy";

        genreEntity.setName(expectedName);

        assertEquals(expectedName, genreEntity.getName());
    }

    @Test
    public void testGetSeries() {
        List<SeriesEntity> expectedSeries = new ArrayList<>();
        expectedSeries.add(seriesEntityMock);
        genreEntity.setSeries(expectedSeries);

        List<SeriesEntity> actualSeries = genreEntity.getSeries();

        assertEquals(expectedSeries, actualSeries);
    }

    @Test
    public void testGetMovies() {
        List<MovieEntity> expectedMovies = new ArrayList<>();
        expectedMovies.add(movieEntityMock);
        genreEntity.setMovies(expectedMovies);

        List<MovieEntity> actualMovies = genreEntity.getMovies();

        assertEquals(expectedMovies, actualMovies);
    }
}