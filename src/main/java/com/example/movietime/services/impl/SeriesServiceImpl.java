package com.example.movietime.services.impl;

import com.example.movietime.domain.dtos.view.SeriesViewDTO;
import com.example.movietime.repositories.SeriesRepository;
import com.example.movietime.services.SeriesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesServiceImpl implements SeriesService {

    private final SeriesRepository seriesRepository;
    private final ModelMapper modelMapper;


    public SeriesServiceImpl(SeriesRepository seriesRepository, ModelMapper modelMapper) {
        this.seriesRepository = seriesRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<SeriesViewDTO> findAllSeriesView() {
        return this.seriesRepository.findAll().stream()
                .map(series -> {
                    SeriesViewDTO seriesViewDTO = this.modelMapper.map(series, SeriesViewDTO.class);
                    seriesViewDTO.setVideoUrl(series.getVideoURL().isEmpty()
                            ? "/images/video-unavailable.png"
                            : series.getVideoURL());
                    return seriesViewDTO;
                }).collect(Collectors.toList());
    }
}
