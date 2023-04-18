package com.example.movietime.web;

import com.example.movietime.domain.dtos.model.GenreDTO;
import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.entities.GenreEntity;
import com.example.movietime.domain.entities.MovieEntity;
import com.example.movietime.services.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testMoviesPageShown() throws Exception {
        mockMvc.perform(get("/movies/all")).
                andExpect(status().isOk()).
                andExpect(view().name("all-movies"));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testFailAddMovie() throws Exception {

        mockMvc.perform(post("/movies/add")
                        .param("id", "1")
                        .param("name", "Test")
                        .param("description", "testtt")
                        .param("videoURL", "ZAXA1DV4dtI")
                        .param("imageURL", "https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies/add"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeleteMovieById() {
        MovieService movieService = Mockito.mock(MovieService.class);

        GenreDTO genre1 = new GenreDTO();
        genre1.setId(1L);
        genre1.setName("genre1");

        GenreDTO genre2 = new GenreDTO();
        genre1.setId(2L);
        genre1.setName("genre2");

        List<GenreDTO> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        MovieDTO movie = new MovieDTO();
        movie.setId(1L);
        movie.setName("Testt");
        movie.setDescription("testttt");
        movie.setReleasedYear(Year.of(2023));
        movie.setVideoURL("EXAMP98LE2");
        movie.setLanguage("English");
        movie.setGenres(genres);
        movie.setImageURL("https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg");

        Mockito.when(movieService.getMovieByIdAndReturnDTO(1L)).thenReturn(movie);


        movieService.deleteMovie(1L);

        Mockito.verify(movieService, Mockito.times(1)).deleteMovie(1L);
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testMoviesAddSuccess() throws Exception {
        mockMvc.perform(post("/movies/add").
                        param("id", "1").
                        param("name", "Testt").
                        param("description", "testtttttttttttt").
                        param("releasedYear", "2023").
                        param("videoURL", "EXAMP98LE2").
                        param("language", "English").
                        param("genres", "Horror","Drama").
                        param("imageUrl", "https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg").
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/movies/all"));
    }

}