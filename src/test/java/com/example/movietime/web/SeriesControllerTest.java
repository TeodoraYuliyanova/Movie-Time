package com.example.movietime.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class SeriesControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testSeriesPageShown() throws Exception {
        mockMvc.perform(get("/series/all")).
                andExpect(status().isOk()).
                andExpect(view().name("all-series"));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testFailAddSeries() throws Exception {

        mockMvc.perform(post("/series/add")
                        .param("id", "1")
                        .param("name", "Test")
                        .param("description", "testtt")
                        .param("releasedYear", "2023")
                        .param("videoURL", "ZAXA1DV4dtI")
                        .param("imageURL", "https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/series/add"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testSeriesAddSuccess() throws Exception {
        mockMvc.perform(post("/series/add").
                        param("id", "1").
                        param("name", "Testt").
                        param("description", "testtttttttttttt").
                        param("releasedYear", "2023").
                        param("episodes","10").
                        param("videoURL", "EXAMP98LE2").
                        param("language", "English").
                        param("genres", "Horror","Drama").
                        param("imageUrl", "https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg").
                        with(csrf())).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/series/all"));
    }



}