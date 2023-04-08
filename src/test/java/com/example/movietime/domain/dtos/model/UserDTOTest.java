package com.example.movietime.domain.dtos.model;

import com.example.movietime.domain.entities.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO();
    }

    @Test
    public void testGetSetId() {
        Long id = 1L;
        userDTO.setId(id);
        assertEquals(id, userDTO.getId());
    }

    @Test
    public void testGetSetFirstName() {
        String firstName = "Testtt";
        userDTO.setFirstName(firstName);
        assertEquals(firstName, userDTO.getFirstName());
    }

    @Test
    public void testGetSetLastName() {
        String lastName = "Testtt";
        userDTO.setLastName(lastName);
        assertEquals(lastName, userDTO.getLastName());
    }

    @Test
    public void testGetSetEmail() {
        String email = "test@example.com";
        userDTO.setEmail(email);
        assertEquals(email, userDTO.getEmail());
    }

    @Test
    public void testGetSetUsername() {
        String username = "testest";
        userDTO.setUsername(username);
        assertEquals(username, userDTO.getUsername());
    }

    @Test
    public void testGetSetPassword() {
        String password = "secret";
        userDTO.setPassword(password);
        assertEquals(password, userDTO.getPassword());
    }

    @Test
    public void testGetSetRoles() {
        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity role1 = Mockito.mock(RoleEntity.class);
        RoleEntity role2 = Mockito.mock(RoleEntity.class);
        roles.add(role1);
        roles.add(role2);
        userDTO.setRoles(roles);
        assertEquals(roles, userDTO.getRoles());
    }

    @Test
    public void testGetSetMyList() {
        MyListDTO myListDTO = Mockito.mock(MyListDTO.class);
        userDTO.setMyList(myListDTO);
        assertEquals(myListDTO, userDTO.getMyList());
    }

}