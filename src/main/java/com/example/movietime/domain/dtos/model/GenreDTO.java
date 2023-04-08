package com.example.movietime.domain.dtos.model;

import jakarta.validation.constraints.NotNull;

public class GenreDTO {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    public GenreDTO() {
    }

    public Long getId() {
        return id;
    }

    public GenreDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GenreDTO setName(String name) {
        this.name = name;
        return this;
    }
}
