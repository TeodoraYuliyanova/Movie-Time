package com.example.movietime.domain.dtos.view;

public class UserDTOFetch {

    private String username;
    private String firstName;
    private String lastName;

    public UserDTOFetch() {
    }

    public String getUsername() {
        return username;
    }

    public UserDTOFetch setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTOFetch setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTOFetch setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
