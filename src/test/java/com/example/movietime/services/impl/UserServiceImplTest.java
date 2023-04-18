package com.example.movietime.services.impl;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.dtos.view.UsersViewDTO;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testIsDbInitShouldReturnTrueWhenUserRepositoryIsEmpty() {
        when(userRepository.count()).thenReturn(0L);

        boolean result = userService.isDbInit();

        assertTrue(result);
    }

    @Test
    public void testIsDbInitShouldReturnFalseWhenUserRepositoryIsNotEmpty() {
        when(userRepository.count()).thenReturn(1L);

        boolean result = userService.isDbInit();

        assertFalse(result);
    }

    @Test
    public void testGetUserByEmailShouldReturnUserDtoWhenUserExistsWithEmail() {
        String email = "admin@example.com";

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail(email);
        userEntity.setFirstName("admin");
        userEntity.setLastName("adminov");
        userEntity.setUsername("admin");

        when(userRepository.findUserEntityByEmail(email)).thenReturn(Optional.of(userEntity));
        UserDTO expectedUserDto = new UserDTO();
        when(modelMapper.map(userEntity, UserDTO.class)).thenReturn(expectedUserDto);


        UserDTO result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(expectedUserDto, result);

        verify(userRepository).findUserEntityByEmail(email);
    }

    @Test
    public void testGetUserByEmailShouldThrowUsernameNotFoundException() {
        String email = "something@example.com";
        when(userRepository.findUserEntityByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getUserByEmail(email));
    }


    @Test
    public void testSaveUser() {
        UserDTO userDto = new UserDTO();
        userDto.setFirstName("Ivan")
                .setLastName("Ivanov")
                .setEmail("vankata@gmail.com")
                .setUsername("vankata");

        userService.saveUser(userDto);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testGetAllUsers() {
        List<UserEntity> userEntities = new ArrayList<>();

        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setFirstName("Ivan");
        user1.setLastName("Ivanov");
        user1.setUsername("vankata");
        user1.setRoles(new ArrayList<>());
        userEntities.add(user1);

        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setFirstName("Gosho");
        user2.setLastName("Goshev");
        user2.setUsername("goshko");
        user2.setRoles(new ArrayList<>());
        userEntities.add(user2);

        when(userRepository.findAll()).thenReturn(userEntities);

        List<UsersViewDTO> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("vankata", users.get(0).getUsername());
        assertEquals("goshko", users.get(1).getUsername());
    }





}