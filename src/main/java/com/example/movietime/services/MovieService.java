package com.example.movietime.services;

import com.example.movietime.domain.dtos.binding.CreateOrUpdateMovieDTO;
import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.view.AllMoviesViewDTO;
import com.example.movietime.domain.entities.MovieEntity;

import java.util.List;

public interface MovieService {
    List<AllMoviesViewDTO> getAllMovies();

    void addMovie(CreateOrUpdateMovieDTO addMovieModel);

    void deleteMovie(Long id);

    MovieDTO getMovieByIdAndReturnDTO(Long id);

    void editMovie(MovieDTO movie, Long id);

    List<MovieDTO> searchMovies(String query);
}
