package com.example.movietime.services;

import com.example.movietime.domain.entities.RoleEntity;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.domain.enums.RoleNameEnum;
import com.example.movietime.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ApplicationUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplicationUserDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new ApplicationUserDetailsService(
                userRepository
        );
    }

    @Test
    void testLoadUserByUsernameUserDoesNotExist() {

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("something@gmail.com")
        );
    }

    @Test
    public void testLoadUserByUsername_ExistingUser_ReturnsUserDetails() {
        String username = "vankata";
        String email = "vanka@example.com";
        String password = "password";

        List<RoleEntity> roles = new ArrayList<>();

        RoleEntity role = new RoleEntity();
        role.setRole(RoleNameEnum.USER);
        roles.add(role);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setRoles(roles);

        when(userRepository.findUserEntityByUsername(username)).thenReturn(Optional.of(userEntity));

        UserDetails result = toTest.loadUserByUsername(username);

        verify(userRepository).findUserEntityByUsername(username);

        assertNotNull(result);
        assertEquals(email, result.getUsername());
        assertEquals(password, result.getPassword());

        Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) result.getAuthorities();
        assertNotNull(authorities);
        assertFalse(authorities.isEmpty());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
    }



}