package com.example.movietime.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
    }

    @Test
    public void testSetFirstNameAndGetFirstName() {
        String firstName = "John";
        userEntity.setFirstName(firstName);
        assertEquals(firstName, userEntity.getFirstName());
    }

    @Test
    public void testSetLastNameAndGetLastName() {
        String lastName = "Doe";
        userEntity.setLastName(lastName);
        assertEquals(lastName, userEntity.getLastName());
    }

    @Test
    public void testSetEmailAndGetEmail() {
        String email = "johndoe@example.com";
        userEntity.setEmail(email);
        assertEquals(email, userEntity.getEmail());
    }

    @Test
    public void testSetUsernameAndGetUsername() {
        String username = "johndoe";
        userEntity.setUsername(username);
        assertEquals(username, userEntity.getUsername());
    }

    @Test
    public void testSetPasswordAndGetPassword() {
        String password = "password";
        userEntity.setPassword(password);
        assertEquals(password, userEntity.getPassword());
    }



    @Test
    public void testSetMyListAndGetMyList() {
        MyListEntity myList = new MyListEntity();
        userEntity.setMyList(myList);
        assertEquals(myList, userEntity.getMyList());
    }

}