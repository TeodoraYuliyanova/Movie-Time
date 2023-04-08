package com.example.movietime.domain.dtos.binding;

import jakarta.validation.constraints.Size;

public class UserLoginFormDTO {

    @Size(min = 4,max = 20)
    private String username;

    @Size(min = 5,max = 20)
    private String password;

    public UserLoginFormDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginFormDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginFormDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
