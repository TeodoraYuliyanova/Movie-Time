package com.example.movietime.domain.dtos.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreDTOTest {

    @Test
    public void testGettersAndSetters() {
        GenreDTO genreDTO = new GenreDTO();

        genreDTO.setId(1L);
        genreDTO.setName("Testtt");


        assertEquals(1L, genreDTO.getId());
        assertEquals("Testtt", genreDTO.getName());


    }

}