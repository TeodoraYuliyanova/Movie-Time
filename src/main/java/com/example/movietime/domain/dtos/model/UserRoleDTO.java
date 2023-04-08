package com.example.movietime.domain.dtos.model;

import jakarta.validation.constraints.NotNull;

public class UserRoleDTO {

    @NotNull
    private Long id;
    private String role;

    public UserRoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserRoleDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserRoleDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
