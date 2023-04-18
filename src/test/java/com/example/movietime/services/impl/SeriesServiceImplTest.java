package com.example.movietime.services.impl;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.CreateOrUpdateSeriesDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.dtos.view.AllSeriesViewDTO;
import com.example.movietime.domain.entities.GenreEntity;
import com.example.movietime.domain.entities.SeriesEntity;
import com.example.movietime.repositories.GenreRepository;
import com.example.movietime.repositories.SeriesRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeriesServiceImplTest {


    @InjectMocks
    private SeriesServiceImpl seriesService;

    @Mock
    private SeriesRepository seriesRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    @Transactional
    public void testAddSeries() {
        CreateOrUpdateSeriesDTO addSeriesDTO = new CreateOrUpdateSeriesDTO();

        addSeriesDTO.setName("Test");
        addSeriesDTO.setReleasedYear(2023);
        addSeriesDTO.setGenres(Arrays.asList("Action", "Drama"));


        when(genreRepository.findGenreByName(anyString())).thenReturn(Optional.of(new GenreEntity()));

        SeriesEntity newSeries = new SeriesEntity();
        when(modelMapper.map(eq(addSeriesDTO), eq(SeriesEntity.class))).thenReturn(newSeries);

        when(seriesRepository.saveAndFlush(any(SeriesEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        seriesService.addSeries(addSeriesDTO);

        verify(genreRepository, times(2)).findGenreByName(anyString());
        verify(seriesRepository, times(1)).saveAndFlush(any(SeriesEntity.class));
    }

    @Test
    public void testEditSeries() {
        Long id = 1L;

        SeriesEntity seriesEntity = new SeriesEntity();
        seriesEntity.setId(id);
        seriesEntity.setName("Test Series");
        seriesEntity.setReleasedYear(Year.of(2022));
        seriesEntity.setEpisodes(5);
        seriesEntity.setLanguage("Spanish");


        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setName("Updated Series");
        seriesDTO.setReleasedYear(Year.of(2023));
        seriesDTO.setEpisodes(10);
        seriesDTO.setLanguage("English");


        when(seriesRepository.findById(id)).thenReturn(Optional.of(seriesEntity));

        when(seriesRepository.save(any(SeriesEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(modelMapper.map(any(SeriesEntity.class), eq(SeriesDTO.class))).thenReturn(seriesDTO);

        seriesService.editSeries(seriesDTO, id);

        verify(seriesRepository, times(1)).findById(id);
        verify(seriesRepository, times(1)).save(any(SeriesEntity.class));
        verify(modelMapper, times(1)).map(any(SeriesEntity.class), eq(SeriesDTO.class));
    }


    @Test
    void testEditSeriesThrowsException() {
        Long seriesID = 1L;
        SeriesDTO seriesDTO = new SeriesDTO();

        when(seriesRepository.findById(seriesID)).thenReturn(java.util.Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> seriesService.editSeries(seriesDTO, seriesID));

        verify(seriesRepository, times(1)).findById(seriesID);
        verify(seriesRepository, never()).save(any(SeriesEntity.class));

    }

    @Test
    public void testDeleteSeries() {
        Long id = 1L;

        SeriesEntity seriesEntity = new SeriesEntity();
        seriesEntity.setId(id);
        seriesEntity.setName("Test Series");
        seriesEntity.setReleasedYear(Year.of(2022));
        seriesEntity.setEpisodes(5);
        seriesEntity.setLanguage("Spanish");


        List<GenreEntity> genres = new ArrayList<>();
        GenreEntity genre1 = new GenreEntity();
        genre1.setName("Action");
        genres.add(genre1);

        GenreEntity genre2 = new GenreEntity();
        genre2.setName("Drama");
        genres.add(genre2);

        seriesEntity.setGenres(genres);

        when(seriesRepository.findById(id)).thenReturn(Optional.of(seriesEntity));

        seriesService.deleteSeries(id);

        verify(seriesRepository, times(1)).findById(id);
        verify(seriesRepository, times(1)).delete(any(SeriesEntity.class));
    }

    @Test
    void findById_shouldThrowObjectNotFoundException_whenSeriesDoesNotExist() {
        Long seriesId = 1L;

        when(seriesRepository.findById(seriesId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> seriesService.getSeriesById(seriesId));
        verify(seriesRepository, times(1)).findById(seriesId);
    }

    @Test
    public void testGetSeriesById() {
        Long id = 1L;

        SeriesEntity seriesEntity = new SeriesEntity();
        seriesEntity.setId(id);
        seriesEntity.setName("Test Series");
        seriesEntity.setReleasedYear(Year.of(2022));
        seriesEntity.setEpisodes(5);
        seriesEntity.setLanguage("Spanish");

        when(seriesRepository.findById(id)).thenReturn(Optional.of(seriesEntity));

        when(modelMapper.map(seriesEntity, SeriesDTO.class)).thenReturn(new SeriesDTO());

        SeriesDTO result = seriesService.getSeriesById(id);

        verify(seriesRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(seriesEntity, SeriesDTO.class);
        assertNotNull(result);
    }


    @Test
    public void testGetAllSeries() {
        GenreEntity genre1 = new GenreEntity();
        genre1.setId(1L);
        genre1.setName("genre1");

        GenreEntity genre2 = new GenreEntity();
        genre1.setId(2L);
        genre1.setName("genre2");

        List<GenreEntity> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        SeriesEntity series1 = new SeriesEntity();
        series1.setId(1L);
        series1.setName("Testt");
        series1.setDescription("testttt");
        series1.setReleasedYear(Year.of(2023));
        series1.setVideoURL("EXAMP98LE2");
        series1.setLanguage("English");
        series1.setGenres(genres);
        series1.setImageURL("https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg");
        series1.setEpisodes(10);

        SeriesEntity series2 = new SeriesEntity();
        series2.setId(2L);
        series2.setName("Testt Test");
        series2.setDescription("testttttt");
        series2.setReleasedYear(Year.of(2022));
        series2.setVideoURL("eXAMP98LE2");
        series2.setLanguage("Spanish");
        series2.setGenres(genres);
        series2.setImageURL("https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg");
        series2.setEpisodes(20);

        List<SeriesEntity> seriesEntities = new ArrayList<>();
        seriesEntities.add(series1);
        seriesEntities.add(series2);

        when(seriesRepository.findAll()).thenReturn(seriesEntities);

        List<AllSeriesViewDTO> result = seriesService.getAllSeries();

        assertEquals(seriesEntities.size(), result.size());

        SeriesEntity seriesEntity = seriesEntities.get(0);
        AllSeriesViewDTO allSeriesViewDTO = result.get(0);

        assertEquals(seriesEntity.getId(), allSeriesViewDTO.getId());
        assertEquals(seriesEntity.getName(), allSeriesViewDTO.getName());
        assertEquals(seriesEntity.getDescription(), allSeriesViewDTO.getDescription());
        assertEquals(String.valueOf(seriesEntity.getReleasedYear()), allSeriesViewDTO.getReleasedYear());
        assertEquals(seriesEntity.getVideoURL(), allSeriesViewDTO.getVideoURL());
        assertEquals(seriesEntity.getLanguage(), allSeriesViewDTO.getLanguage());
        assertEquals(seriesEntity.getImageURL(), allSeriesViewDTO.getImageURL());
    }


    @Test
    public void testSearchSeries() {
        String query = "test";
        List<SeriesEntity> seriesEntities = Arrays.asList(new SeriesEntity(), new SeriesEntity());
        List<SeriesDTO> seriesDTOs = seriesEntities.stream()
                .map(seriesEntity -> modelMapper.map(seriesEntity, SeriesDTO.class))
                .collect(Collectors.toList());

        when(seriesRepository.findByNameContainingIgnoreCase(query)).thenReturn(seriesEntities);

        List<SeriesDTO> result = seriesService.searchSeries(query);

        verify(seriesRepository, times(1)).findByNameContainingIgnoreCase(query);

        assertEquals(seriesDTOs, result);
    }

}