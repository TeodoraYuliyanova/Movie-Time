package com.example.movietime.domain.entities;

import com.example.movietime.domain.enums.RoleNameEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class RoleEntityTest {

    @InjectMocks
    private RoleEntity roleEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRole() {
        RoleNameEnum expectedRole = RoleNameEnum.ADMIN;
        roleEntity.setRole(expectedRole);

        RoleNameEnum actualRole = roleEntity.getRole();

        assertEquals(expectedRole, actualRole);
    }

    @Test
    public void testSetRole() {
        RoleNameEnum expectedRole = RoleNameEnum.USER;

        roleEntity.setRole(expectedRole);

        assertEquals(expectedRole, roleEntity.getRole());
    }

}