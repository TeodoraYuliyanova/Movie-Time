package com.example.movietime.services.impl;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.CreateOrUpdateSeriesDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.dtos.view.AllSeriesViewDTO;
import com.example.movietime.domain.entities.GenreEntity;
import com.example.movietime.domain.entities.SeriesEntity;
import com.example.movietime.repositories.GenreRepository;
import com.example.movietime.repositories.SeriesRepository;
import com.example.movietime.services.SeriesService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;



    @Autowired
    public SeriesServiceImpl(SeriesRepository seriesRepository, ModelMapper modelMapper, GenreRepository genreRepository) {
        this.seriesRepository = seriesRepository;
        this.modelMapper = modelMapper;
        this.genreRepository = genreRepository;
    }


    @Override
    public List<AllSeriesViewDTO> getAllSeries() {
        return
                this.seriesRepository.
                        findAll()
                        .stream()
                        .map(this::map).collect(Collectors.toList());
    }

    private AllSeriesViewDTO map(SeriesEntity seriesEntity) {
        return new AllSeriesViewDTO()
                .setId(seriesEntity.getId())
                .setName(seriesEntity.getName())
                .setDescription(seriesEntity.getDescription())
                .setReleasedYear(String.valueOf(seriesEntity.getReleasedYear()))
                .setVideoURL(seriesEntity.getVideoURL())
                .setLanguage(seriesEntity.getLanguage())
                .setEpisodes(seriesEntity.getEpisodes())
                .setImageURL(seriesEntity.getImageURL());

    }

    @Override
    @Transactional
    public void addSeries(CreateOrUpdateSeriesDTO addSeriesDTO) {
        int yearInt = addSeriesDTO.getReleasedYear();
        Year released_year = Year.of(yearInt);

        SeriesEntity newSeries = this.modelMapper.map(addSeriesDTO, SeriesEntity.class);

        newSeries.setReleasedYear(released_year);

        List<GenreEntity> genres = addSeriesDTO.getGenres().stream()
                .map(name -> this.genreRepository.findGenreByName(name)
                        .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + name)))
                .collect(Collectors.toList());

        newSeries.setGenres(genres);

        this.seriesRepository.saveAndFlush(newSeries);
        mapEntityToDTO(newSeries);
    }


    @Override
    public void editSeries(SeriesDTO series, Long id) {
        SeriesEntity seriesEntity = getSeries(id);

        seriesEntity.setName(series.getName());
        seriesEntity.setReleasedYear(series.getReleasedYear());
        seriesEntity.setEpisodes(series.getEpisodes());
        seriesEntity.setLanguage(series.getLanguage());


        this.seriesRepository.save(seriesEntity);
        mapEntityToDTO(seriesEntity);

    }

    @Override
    public void deleteSeries(Long id) {
        SeriesEntity seriesEntity = getSeries(id);

        seriesEntity.getGenres().clear();
        this.seriesRepository.delete(seriesEntity);
    }

    @Override
    public SeriesDTO getSeriesById(Long id) {
        SeriesEntity series = getSeries(id);

        return this.modelMapper.map(series,SeriesDTO.class);
    }

    @Override
    public List<SeriesDTO> searchSeries(String query) {
        return this.seriesRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(seriesEntity -> this.modelMapper.map(seriesEntity,SeriesDTO.class))
                .collect(Collectors.toList());
    }

    private void mapEntityToDTO(SeriesEntity seriesEntity) {
        this.modelMapper.map(seriesEntity, SeriesDTO.class);
    }

    private SeriesEntity getSeries(Long id) {
        return this.seriesRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "series"));
    }
}

