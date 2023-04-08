package com.example.movietime.domain.dtos.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileViewModelTest {

    private UserProfileViewModel userProfileViewModel;

    @BeforeEach
    public void setUp() {
        userProfileViewModel = new UserProfileViewModel();
    }

    @Test
    public void testGetSetId() {
        Long id = 1L;
        userProfileViewModel.setId(id);
        assertEquals(id, userProfileViewModel.getId());
    }

    @Test
    public void testGetSetFirstName() {
        String firstName = "John";
        userProfileViewModel.setFirstName(firstName);
        assertEquals(firstName, userProfileViewModel.getFirstName());
    }

    @Test
    public void testGetSetLastName() {
        String lastName = "Doe";
        userProfileViewModel.setLastName(lastName);
        assertEquals(lastName, userProfileViewModel.getLastName());
    }

    @Test
    public void testGetSetUsername() {
        String username = "john.doe";
        userProfileViewModel.setUsername(username);
        assertEquals(username, userProfileViewModel.getUsername());
    }

    @Test
    public void testGetSetEmail() {
        String email = "john.doe@example.com";
        userProfileViewModel.setEmail(email);
        assertEquals(email, userProfileViewModel.getEmail());
    }

}