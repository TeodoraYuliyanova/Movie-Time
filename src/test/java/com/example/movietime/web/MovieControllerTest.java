package com.example.movietime.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

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

}