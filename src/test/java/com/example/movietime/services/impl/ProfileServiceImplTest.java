package com.example.movietime.services.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    private ProfileServiceImpl profileService;
    private UserService userService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        modelMapper = mock(ModelMapper.class);
        profileService = new ProfileServiceImpl(userService, modelMapper);
    }

    @Test
    public void testSetAndSaveNewDetailsForUser() {
        String email = "test@test.com";

        UserDTO existingUserDTO = new UserDTO();
        existingUserDTO.setId(1L);
        existingUserDTO.setUsername("existingUser");
        existingUserDTO.setEmail("existing@example.com");

        UserDTO newUserDTO = new UserDTO();
        newUserDTO.setId(1L);
        newUserDTO.setUsername("newUser");
        newUserDTO.setEmail("new@example.com");

        UserEntity existingUserEntity = new UserEntity();

        when(userService.getUserByEmail(email)).thenReturn(existingUserDTO);

        when(modelMapper.map(existingUserDTO, UserEntity.class)).thenReturn(existingUserEntity);

        when(modelMapper.map(existingUserEntity, UserDTO.class)).thenReturn(newUserDTO);

        profileService.setAndSaveNewDetailsForUser(newUserDTO, email);

        verify(userService, times(1)).getUserByEmail(email);
        verify(modelMapper, times(1)).map(existingUserDTO, UserEntity.class);
        verify(modelMapper, times(1)).map(existingUserEntity, UserDTO.class);
        verify(userService, times(1)).saveUser(newUserDTO);
    }

}