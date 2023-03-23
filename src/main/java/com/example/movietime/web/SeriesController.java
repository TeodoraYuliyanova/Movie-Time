package com.example.movietime.web;

import com.example.movietime.domain.dtos.view.SeriesViewDTO;
import com.example.movietime.services.SeriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/all")
    public String getSeries(Model model){

        List<SeriesViewDTO> seriesViewDTOS = this.seriesService.findAllSeriesView();

        model.addAttribute("series" , seriesViewDTOS);
        return "all-series";
    }
}
