package com.example.movietime.services.impl;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.UserRegisterFormDto;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.dtos.model.UserRoleDTO;
import com.example.movietime.domain.dtos.model.UsersRestDTO;
import com.example.movietime.domain.dtos.view.UserDTOFetch;
import com.example.movietime.domain.dtos.view.UsersViewDTO;
import com.example.movietime.domain.entities.RoleEntity;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.domain.enums.RoleNameEnum;
import com.example.movietime.repositories.RoleRepository;
import com.example.movietime.repositories.UserRepository;
import com.example.movietime.services.DataBaseInitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


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


}