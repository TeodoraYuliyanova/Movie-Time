package com.example.movietime.domain.dtos.model;

import com.example.movietime.domain.entities.MyListEntity;
import com.example.movietime.domain.entities.RoleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {

    @NotNull
    private Long id;
    @NotNull
    @Size(min = 4,max = 20)
    private String firstName;

    @NotNull
    @Size(min = 4,max = 20)
    private String lastName;

    @Email
    @NotNull
    @Size(min = 5)
    private String email;

    @NotNull
    @Size(min = 4,max = 20)
    private String username;

    @NotNull
    private String password;

    private List<RoleEntity> roles;

    private MyListDTO myList;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public MyListDTO getMyList() {
        return myList;
    }

    public UserDTO setMyList(MyListDTO myList) {
        this.myList = myList;
        return this;
    }
}
