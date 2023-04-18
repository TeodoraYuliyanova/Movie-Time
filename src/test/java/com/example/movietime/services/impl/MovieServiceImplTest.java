package com.example.movietime.services.impl;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.CreateOrUpdateMovieDTO;
import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.view.AllMoviesViewDTO;
import com.example.movietime.domain.entities.GenreEntity;
import com.example.movietime.domain.entities.MovieEntity;
import com.example.movietime.events.MovieAddedEvent;
import com.example.movietime.repositories.GenreRepository;
import com.example.movietime.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private  ApplicationEventPublisher eventPublisher;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testAddMovieWithEvent() {
        CreateOrUpdateMovieDTO addMovieDTO = new CreateOrUpdateMovieDTO();
        addMovieDTO.setName("Test");
        addMovieDTO.setReleasedYear(2023);
        addMovieDTO.setGenres(Arrays.asList("Action", "Drama"));

        when(genreRepository.findGenreByName(anyString())).thenReturn(Optional.of(new GenreEntity()));

        MovieEntity newMovie = new MovieEntity();
        when(modelMapper.map(eq(addMovieDTO), eq(MovieEntity.class))).thenReturn(newMovie);

        when(movieRepository.saveAndFlush(any(MovieEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ArgumentCaptor<MovieAddedEvent> movieAddedEventCaptor = ArgumentCaptor.forClass(MovieAddedEvent.class);

        movieService.addMovie(addMovieDTO);

        verify(genreRepository, times(2)).findGenreByName(anyString());
        verify(movieRepository, times(1)).saveAndFlush(any(MovieEntity.class));

        verify(eventPublisher, times(1)).publishEvent(movieAddedEventCaptor.capture());
        MovieAddedEvent movieAddedEvent = movieAddedEventCaptor.getValue();
        assertNotNull(movieAddedEvent);
        assertEquals(newMovie, movieAddedEvent.getMovieEntity());
    }


    @Test
    public void testEditMovie() {
        Long id = 1L;

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(id);
        movieEntity.setName("Test Series");
        movieEntity.setReleasedYear(Year.of(2022));
        movieEntity.setLanguage("Spanish");


        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setName("Updated Series");
        movieDTO.setReleasedYear(Year.of(2023));
        movieDTO.setLanguage("English");


        when(movieRepository.findById(id)).thenReturn(Optional.of(movieEntity));

        when(movieRepository.save(any(MovieEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(modelMapper.map(any(MovieEntity.class), eq(MovieDTO.class))).thenReturn(movieDTO);

        movieService.editMovie(movieDTO, id);

        verify(movieRepository, times(1)).findById(id);
        verify(movieRepository, times(1)).save(any(MovieEntity.class));
        verify(modelMapper, times(1)).map(any(MovieEntity.class), eq(MovieDTO.class));
    }

    @Test
    void testEditMovieThrowsException() {
        Long movieID = 1L;
        MovieDTO movieDTO = new MovieDTO();

        when(movieRepository.findById(movieID)).thenReturn(java.util.Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> movieService.editMovie(movieDTO, movieID));

        verify(movieRepository, times(1)).findById(movieID);
        verify(movieRepository, never()).save(any(MovieEntity.class));

    }

    @Test
    public void testGetAllMovies() {
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("genre1");

        GenreEntity genre2 = new GenreEntity();
        genre1.setId(2L);
        genre1.setName("genre2");

        List<GenreEntity> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        MovieEntity movie1 = new MovieEntity();
        movie1.setId(1L);
        movie1.setName("Testt");
        movie1.setDescription("testttt");
        movie1.setReleasedYear(Year.of(2023));
        movie1.setVideoURL("EXAMP98LE2");
        movie1.setLanguage("English");
        movie1.setGenres(genres);
        movie1.setImageURL("https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg");

        MovieEntity movie2 = new MovieEntity();
        movie1.setId(2L);
        movie1.setName("Testt Test");
        movie1.setDescription("testttttt");
        movie1.setReleasedYear(Year.of(2022));
        movie1.setVideoURL("eXAMP98LE2");
        movie1.setLanguage("Spanish");
        movie1.setGenres(genres);
        movie1.setImageURL("https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg");

        List<MovieEntity> movieEntities = new ArrayList<>();
        movieEntities.add(movie1);
        movieEntities.add(movie2);

        when(movieRepository.findAll()).thenReturn(movieEntities);

        List<AllMoviesViewDTO> result = movieService.getAllMovies();

        assertEquals(movieEntities.size(), result.size(), "Size of returned list should match size of mocked list");

        MovieEntity movieEntity = movieEntities.get(0);
        AllMoviesViewDTO allMoviesViewDTO = result.get(0);

        assertEquals(movieEntity.getId(), allMoviesViewDTO.getId(), "Id should match");
        assertEquals(movieEntity.getName(), allMoviesViewDTO.getName(), "Name should match");
        assertEquals(movieEntity.getDescription(), allMoviesViewDTO.getDescription(), "Description should match");
        assertEquals(String.valueOf(movieEntity.getReleasedYear()), allMoviesViewDTO.getReleasedYear(), "Released year should match");
        assertEquals(movieEntity.getVideoURL(), allMoviesViewDTO.getVideoURL(), "Video URL should match");
        assertEquals(movieEntity.getLanguage(), allMoviesViewDTO.getLanguage(), "Language should match");
        assertEquals(movieEntity.getImageURL(), allMoviesViewDTO.getImageURL(), "Image URL should match");
    }

    @Test
    public void testGetMovieByIdAndReturnDTO() {
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("genre1");

        List<GenreEntity> genres = new ArrayList<>();
        genres.add(genre1);

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1L);
        movieEntity.setName("Testt");
        movieEntity.setDescription("testttt");
        movieEntity.setReleasedYear(Year.of(2023));
        movieEntity.setVideoURL("EXAMP98LE2");
        movieEntity.setLanguage("English");
        movieEntity.setGenres(genres);
        movieEntity.setImageURL("https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg");

        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieEntity));

        MovieDTO expectedDto = new MovieDTO();
        when(modelMapper.map(any(), any())).thenReturn(expectedDto);

        MovieDTO result = movieService.getMovieByIdAndReturnDTO(movieEntity.getId());

        assertEquals(expectedDto, result);
    }

    @Test
    public void testDeleteMovie() {
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("genre1");

        List<GenreEntity> genres = new ArrayList<>();
        genres.add(genre1);

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1L);
        movieEntity.setName("Testt");
        movieEntity.setDescription("testttt");
        movieEntity.setReleasedYear(Year.of(2023));
        movieEntity.setVideoURL("EXAMP98LE2");
        movieEntity.setLanguage("English");
        movieEntity.setGenres(genres);
        movieEntity.setImageURL("https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg");

        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movieEntity));

        doNothing().when(movieRepository).delete(movieEntity);

        movieService.deleteMovie(movieEntity.getId());
        verify(movieRepository).delete(movieEntity);
    }

    @Test
    void findById_shouldThrowObjectNotFoundException_whenMovieDoesNotExist() {
        Long movieId = 1L;

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> movieService.getMovieByIdAndReturnDTO(movieId));
        verify(movieRepository, times(1)).findById(movieId);
    }


    @Test
    public void testSearchMovies() {
        String query = "test";
        List<MovieEntity> movieEntities = Arrays.asList(new MovieEntity(), new MovieEntity());
        List<MovieDTO> movieDTOs = movieEntities.stream()
                .map(movieEntity -> modelMapper.map(movieEntity, MovieDTO.class))
                .collect(Collectors.toList());

        when(movieRepository.findByNameContainingIgnoreCase(query)).thenReturn(movieEntities);

        List<MovieDTO> result = movieService.searchMovies(query);

        verify(movieRepository, times(1)).findByNameContainingIgnoreCase(query);

        assertEquals(movieDTOs, result);
    }



}