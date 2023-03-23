package com.example.movietime.services;

import com.example.movietime.domain.dtos.view.SeriesViewDTO;

import java.util.List;

public interface SeriesService {

    List<SeriesViewDTO> findAllSeriesView();
}
