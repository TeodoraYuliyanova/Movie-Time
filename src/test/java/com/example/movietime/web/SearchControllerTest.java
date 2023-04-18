package com.example.movietime.web;

import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.services.MovieService;
import com.example.movietime.services.SeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private MovieService movieService;

    @Mock
    private SeriesService seriesService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    public void testSearch() throws Exception {
        String query = "test";
        List<MovieDTO> movies = Arrays.asList(new MovieDTO(), new MovieDTO());
        List<SeriesDTO> series = Arrays.asList(new SeriesDTO(), new SeriesDTO());

        when(movieService.searchMovies(query)).thenReturn(movies);
        when(seriesService.searchSeries(query)).thenReturn(series);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/search")
                        .param("query", query))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("search-results"))
                .andReturn();

        ModelMap modelMap = Objects.requireNonNull(result.getModelAndView()).getModelMap();
        List<MovieDTO> moviesResult = (List<MovieDTO>) modelMap.get("movies");
        List<SeriesDTO> seriesResult = (List<SeriesDTO>) modelMap.get("series");
        String errorMessageResult = (String) modelMap.get("errorMessage");

        assertEquals(movies, moviesResult);
        assertEquals(series, seriesResult);
        assertNull(errorMessageResult);

        verify(movieService, times(1)).searchMovies(query);
        verify(seriesService, times(1)).searchSeries(query);
    }

    @Test
    public void testSearchWithNoResults() throws Exception {
        String query = "nonexistent";
        List<MovieDTO> movies = Collections.emptyList();
        List<SeriesDTO> series = Collections.emptyList();

        when(movieService.searchMovies(query)).thenReturn(movies);
        when(seriesService.searchSeries(query)).thenReturn(series);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/search")
                        .param("query", query))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("search-results"))
                .andReturn();

        ModelMap modelMap = Objects.requireNonNull(result.getModelAndView()).getModelMap();
        List<MovieDTO> moviesResult = (List<MovieDTO>) modelMap.get("movies");
        List<SeriesDTO> seriesResult = (List<SeriesDTO>) modelMap.get("series");
        String errorMessageResult = (String) modelMap.get("errorMessage");

        assertEquals(movies, moviesResult);
        assertEquals(series, seriesResult);
        assertEquals("No movies or series found with the given name.", errorMessageResult);

        verify(movieService, times(1)).searchMovies(query);
        verify(seriesService, times(1)).searchSeries(query);
    }

}