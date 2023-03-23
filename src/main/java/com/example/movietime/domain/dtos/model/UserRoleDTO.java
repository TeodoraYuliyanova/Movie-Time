package com.example.movietime.domain.dtos.model;

public class UserRoleDTO {

    private String id;
    private String role;

    public UserRoleDTO() {
    }

    public String getId() {
        return id;
    }

    public UserRoleDTO setId(String id) {
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
