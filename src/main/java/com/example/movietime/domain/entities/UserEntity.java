package com.example.movietime.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{


    @Column(name = "first_name",nullable = false)
    @Size(min = 4,max = 20)
    private String firstName;

    @Column(name = "last_name",nullable = false)
    @Size(min = 4,max = 20)
    private String lastName;

    @Email
    @Column(nullable = false,unique = true)
    @Size(min = 5)
    private String email;

    @Column(nullable = false,unique = true)
    @Size(min = 4,max = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<RoleEntity> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MyListEntity myList;

    public UserEntity() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<RoleEntity> role) {
        this.roles = role;
        return this;
    }

    public UserEntity addRole(RoleEntity roleName) {
        this.roles.add(roleName);
        return this;
    }

    public MyListEntity getMyList() {
        return myList;
    }

    public UserEntity setMyList(MyListEntity myList) {
        this.myList = myList;
        return this;
    }
}
