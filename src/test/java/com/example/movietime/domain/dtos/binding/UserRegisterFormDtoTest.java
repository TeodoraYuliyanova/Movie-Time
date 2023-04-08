package com.example.movietime.domain.dtos.binding;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class UserRegisterFormDtoTest {

    @Test
    public void testGettersAndSetters() {
        UserRegisterFormDto userRegisterFormDto = new UserRegisterFormDto();

        userRegisterFormDto.setFirstName("Testtt");
        userRegisterFormDto.setLastName("Testtt");
        userRegisterFormDto.setEmail("test@gmail.com");
        userRegisterFormDto.setPassword("secret");
        userRegisterFormDto.setConfirmPassword("secret");
        userRegisterFormDto.setUsername("testest");

        assertEquals("Testtt", userRegisterFormDto.getFirstName());
        assertEquals("Testtt", userRegisterFormDto.getLastName());
        assertEquals("test@gmail.com", userRegisterFormDto.getEmail());
        assertEquals("secret", userRegisterFormDto.getPassword());
        assertEquals("secret", userRegisterFormDto.getConfirmPassword());
        assertEquals("testest", userRegisterFormDto.getUsername());


    }


}