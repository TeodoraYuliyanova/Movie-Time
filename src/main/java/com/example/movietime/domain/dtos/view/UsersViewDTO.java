package com.example.movietime.domain.dtos.view;

import com.example.movietime.domain.dtos.model.UserRoleDTO;

import java.util.Collections;
import java.util.List;

public class UsersViewDTO {

    private Long id;

    private String username;

    private List<UserRoleDTO> roles;

    public UsersViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public UsersViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UsersViewDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<UserRoleDTO> getRoles() {
        return Collections.unmodifiableList(roles);
    }

    public UsersViewDTO setRoles(List<UserRoleDTO> roles) {
        this.roles = roles;
        return this;
    }
}
