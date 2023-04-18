package com.example.movietime.web;

import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.entities.MovieEntity;
import com.example.movietime.domain.entities.SeriesEntity;
import com.example.movietime.services.MovieService;
import com.example.movietime.services.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final MovieService movieService;
    private final SeriesService seriesService;

    @Autowired
    public SearchController(MovieService movieService, SeriesService seriesService) {
        this.movieService = movieService;
        this.seriesService = seriesService;
    }


    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<MovieDTO> movies = this.movieService.searchMovies(query);
        List<SeriesDTO> series = this.seriesService.searchSeries(query);

        model.addAttribute("movies", movies);
        model.addAttribute("series", series);

        if (movies.isEmpty() && series.isEmpty()) {
            model.addAttribute("errorMessage", "No movies or series found with the given name.");
        }

        return "search-results";
    }
}
