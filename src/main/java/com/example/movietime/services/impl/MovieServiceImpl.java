package com.example.movietime.services.impl;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.CreateOrUpdateMovieDTO;
import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.view.AllMoviesViewDTO;
import com.example.movietime.domain.entities.GenreEntity;
import com.example.movietime.domain.entities.MovieEntity;
import com.example.movietime.repositories.GenreRepository;
import com.example.movietime.repositories.MovieRepository;
import com.example.movietime.services.MovieService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;


    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
    }


    @Override
    public List<AllMoviesViewDTO> getAllMovies() {
        return
                this.movieRepository.
                        findAll()
                        .stream()
                        .map(this::map).collect(Collectors.toList());
    }
    private AllMoviesViewDTO map(MovieEntity movieEntity) {
        return new AllMoviesViewDTO()
                .setId(movieEntity.getId())
                .setName(movieEntity.getName())
                .setDescription(movieEntity.getDescription())
                .setReleasedYear(String.valueOf(movieEntity.getReleasedYear()))
                .setVideoURL(movieEntity.getVideoURL())
                .setLanguage(movieEntity.getLanguage())
                .setImageURL(movieEntity.getImageURL());
    }

    @Override
    @Transactional
    public void addMovie(CreateOrUpdateMovieDTO addMovieDTO) {
        int yearInt = addMovieDTO.getReleasedYear();
        Year released_year = Year.of(yearInt);

        MovieEntity newMovie = this.modelMapper.map(addMovieDTO, MovieEntity.class);

        newMovie.setReleasedYear(released_year);

        List<GenreEntity> genres = addMovieDTO.getGenres().stream()
                .map(name -> this.genreRepository.findGenreByName(name)
                        .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + name)))
                .collect(Collectors.toList());

        newMovie.setGenres(genres);

        this.movieRepository.saveAndFlush(newMovie);
        mapMovieEntityToDTO(newMovie);
    }

    @Override
    public void editMovie(MovieDTO movie, Long id) {
        MovieEntity movieEntity = getMovie(id);

        movieEntity.setName(movie.getName());
        movieEntity.setReleasedYear(movie.getReleasedYear());
        movieEntity.setLanguage(movie.getLanguage());

        this.movieRepository.save(movieEntity);
        mapMovieEntityToDTO(movieEntity);

    }

    @Override
    public void deleteMovie(Long id) {
        MovieEntity movie = getMovie(id);

        movie.getGenres().clear();
        this.movieRepository.delete(movie);
    }

    @Override
    public MovieDTO getMovieByIdAndReturnDTO(Long id) {
        MovieEntity movieEntity = getMovie(id);

       return this.modelMapper.map(movieEntity,MovieDTO.class);
    }

    private void mapMovieEntityToDTO(MovieEntity movie) {
        this.modelMapper.map(movie, MovieDTO.class);
    }

    private MovieEntity getMovie(Long id) {
        return this.movieRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "movie"));
    }
}
