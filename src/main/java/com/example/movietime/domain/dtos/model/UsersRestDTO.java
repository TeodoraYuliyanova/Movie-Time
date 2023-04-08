package com.example.movietime.domain.dtos.model;

public class UsersRestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;

    public UsersRestDTO(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }

    public UsersRestDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersRestDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersRestDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersRestDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UsersRestDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
