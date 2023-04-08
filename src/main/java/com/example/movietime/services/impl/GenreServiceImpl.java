package com.example.movietime.services.impl;

import com.example.movietime.repositories.GenreRepository;
import com.example.movietime.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;


    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


}
