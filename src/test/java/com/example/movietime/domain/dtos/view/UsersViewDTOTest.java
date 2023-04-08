package com.example.movietime.domain.dtos.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UsersViewDTOTest {

    private UsersViewDTO usersViewDTO;
    private Long id;
    private String username;

    @BeforeEach
    public void setUp() {
        usersViewDTO = new UsersViewDTO();
        id = 1L;
        username = "john.doe";
    }

    @Test
    public void testGetSetId() {
        usersViewDTO.setId(id);
        assertEquals(id, usersViewDTO.getId());
    }

    @Test
    public void testGetSetUsername() {
        usersViewDTO.setUsername(username);
        assertEquals(username, usersViewDTO.getUsername());
    }



}