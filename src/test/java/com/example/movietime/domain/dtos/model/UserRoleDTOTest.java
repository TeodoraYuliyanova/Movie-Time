package com.example.movietime.domain.dtos.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRoleDTOTest {

    private UserRoleDTO userRoleDTO;

    @BeforeEach
    public void setUp() {
        userRoleDTO = new UserRoleDTO();
    }

    @Test
    public void testGetSetId() {
        Long id = 1L;
        userRoleDTO.setId(id);
        assertEquals(id, userRoleDTO.getId());
    }

    @Test
    public void testGetSetRole() {
        String role = "ROLE_ADMIN";
        userRoleDTO.setRole(role);
        assertEquals(role, userRoleDTO.getRole());
    }

}