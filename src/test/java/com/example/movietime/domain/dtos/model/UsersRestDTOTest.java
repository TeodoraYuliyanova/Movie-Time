package com.example.movietime.domain.dtos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersRestDTOTest {

    private UsersRestDTO usersRestDTO;

    @BeforeEach
    public void setUp() {
        usersRestDTO = new UsersRestDTO();
    }

    @Test
    public void testGetSetFirstName() {
        String firstName = "Testt";
        usersRestDTO.setFirstName(firstName);
        assertEquals(firstName, usersRestDTO.getFirstName());
    }

    @Test
    public void testGetSetLastName() {
        String lastName = "Testtt";
        usersRestDTO.setLastName(lastName);
        assertEquals(lastName, usersRestDTO.getLastName());
    }

    @Test
    public void testGetSetEmail() {
        String email = "test@example.com";
        usersRestDTO.setEmail(email);
        assertEquals(email, usersRestDTO.getEmail());
    }

    @Test
    public void testGetSetUsername() {
        String username = "testets";
        usersRestDTO.setUsername(username);
        assertEquals(username, usersRestDTO.getUsername());
    }

}