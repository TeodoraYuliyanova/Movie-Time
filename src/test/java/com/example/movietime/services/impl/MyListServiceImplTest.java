package com.example.movietime.services.impl;

import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.entities.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.model.MyListDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;

import com.example.movietime.repositories.MyListRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyListServiceImplTest {

    @InjectMocks
    private MyListServiceImpl myListService;

    @Mock
    private MyListRepository myListRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testAddSeriesToMyListAndSave() {
        MyListDTO myListDTO = new MyListDTO();
        myListDTO.setId(1L);

        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setId(1L);

        SeriesEntity seriesEntity = new SeriesEntity();
        seriesEntity.setId(1L);

        MyListEntity myListEntity = new MyListEntity();
        myListEntity.setId(1L);

        List<SeriesEntity> seriesList = new ArrayList<>();
        seriesList.add(seriesEntity);
        myListEntity.setSeries(seriesList);

        when(modelMapper.map(seriesDTO, SeriesEntity.class)).thenReturn(seriesEntity);
        when(modelMapper.map(myListDTO, MyListEntity.class)).thenReturn(myListEntity);
        when(myListRepository.save(any(MyListEntity.class))).thenReturn(myListEntity);

        myListService.addSeriesToMyListAndSave(myListDTO, seriesDTO);

        verify(myListRepository, times(1)).save(any(MyListEntity.class));
    }

    @Test
    public void testAddMovieToMyListAndSave() {
        MyListDTO myListDTO = new MyListDTO();
        myListDTO.setId(1L);

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1L);

        MyListEntity myListEntity = new MyListEntity();
        myListEntity.setId(1L);

        List<MovieEntity> movieList = new ArrayList<>();
        movieList.add(movieEntity);
        myListEntity.setMovies(movieList);

        when(modelMapper.map(movieDTO, MovieEntity.class)).thenReturn(movieEntity);
        when(modelMapper.map(myListDTO, MyListEntity.class)).thenReturn(myListEntity);
        when(myListRepository.save(any(MyListEntity.class))).thenReturn(myListEntity);

        myListService.addMovieToMyListAndSave(myListDTO, movieDTO);

        verify(myListRepository, times(1)).save(any(MyListEntity.class));
    }

    @Test
    public void testSaveForSchedule() {
        MyListEntity myListEntity = new MyListEntity();
        myListEntity.setId(1L);

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1L);
        movieEntity.setName("Test Series");
        movieEntity.setReleasedYear(Year.of(2022));
        movieEntity.setLanguage("Spanish");


        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("genre1");

        GenreEntity genre2 = new GenreEntity();
        genre1.setId(2L);
        genre1.setName("genre2");

        List<GenreEntity> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        movieEntity.setGenres(genres);
        List<MovieEntity> movies = new ArrayList<>();
        movies.add(movieEntity);
        myListEntity.setMovies(movies);

        when(myListRepository.save(eq(myListEntity))).thenReturn(myListEntity);

        myListService.saveForSchedule(myListEntity);

        verify(myListRepository).save(eq(myListEntity));

    }

    @Test
    public void testMapUserDTOToUserEntity_MapsUserDTOToUserEntity() {
        UserDTO currentUserDTO = new UserDTO();
        currentUserDTO.setId(1L);
        currentUserDTO.setUsername("Test User");
        currentUserDTO.setEmail("test@example.com");
        currentUserDTO.setFirstName("testest");
        currentUserDTO.setLastName("testets");

        ReflectionTestUtils.invokeMethod(myListService, "mapUserDTOToUserEntity", currentUserDTO);

    }

}