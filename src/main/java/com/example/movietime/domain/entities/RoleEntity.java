package com.example.movietime.domain.entities;

import com.example.movietime.domain.enums.RoleNameEnum;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Column
    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;
    public RoleEntity(){
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public RoleEntity setRole(RoleNameEnum role) {
        this.role = role;
        return this;
    }

}
