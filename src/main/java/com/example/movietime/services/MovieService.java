package com.example.movietime.services;

import com.example.movietime.domain.dtos.binding.CreateOrUpdateMovieDTO;
import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.view.AllMoviesViewDTO;

import java.util.List;

public interface MovieService {
    List<AllMoviesViewDTO> getAllMovies();

    void addMovie(CreateOrUpdateMovieDTO addMovieModel);

    void deleteMovie(Long id);

    MovieDTO getMovieByIdAndReturnDTO(Long id);

    void editMovie(MovieDTO movie, Long id);

}
