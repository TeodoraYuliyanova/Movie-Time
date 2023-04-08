package com.example.movietime.services;

import com.example.movietime.domain.dtos.binding.CreateOrUpdateSeriesDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.dtos.view.AllSeriesViewDTO;

import java.util.List;

public interface SeriesService {

    List<AllSeriesViewDTO> getAllSeries();

    void addSeries(CreateOrUpdateSeriesDTO addSeriesModel);

    void editSeries(SeriesDTO series, Long id);

    void deleteSeries(Long id);

    SeriesDTO getSeriesById(Long id);

}
