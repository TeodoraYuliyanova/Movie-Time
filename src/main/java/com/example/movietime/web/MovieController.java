package com.example.movietime.web;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.CreateOrUpdateMovieDTO;
import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.view.AllMoviesViewDTO;
import com.example.movietime.services.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

import static com.example.movietime.outputs.SuccessMessages.*;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public String getAllMovies(Model model) {
        List<AllMoviesViewDTO> moviesViewDTOS = this.movieService.getAllMovies();

        model.addAttribute("movies", moviesViewDTOS);
        return "all-movies";
    }

    @GetMapping("/{id}")
    public String getMovieDetails(@PathVariable("id") Long id,
                                  Model model) {

        MovieDTO movieDTO = this.movieService.getMovieByIdAndReturnDTO(id);

        checkIfIsNullAndAddAttribute(id, model, movieDTO);

        return "details-movie";
    }

    @GetMapping("/add")
    public String addMovie(Model model) {
        if (!model.containsAttribute("addMovieModel")) {
            model.addAttribute("addMovieModel", new CreateOrUpdateMovieDTO());
        }
        List<String> genres = Arrays.asList
                ("Action", "Crime", "Adventure", "Comedy", "Drama", "Fantasy",
                        "Horror", "Mystery", "Romance", "Science Fiction", "Thriller");
        model.addAttribute("genres", genres);

        return "add-movie";
    }


    @PostMapping("/add")
    public String addMovie(@Valid CreateOrUpdateMovieDTO addMovieModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addMovieModel", addMovieModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addMovieModel",
                    bindingResult);
            return "redirect:/movies/add";
        }

        addMovieModel.setGenres(Arrays.asList(request.getParameterValues("genres")));

        this.movieService.addMovie(addMovieModel);

        addFlashAttribute(redirectAttributes, String.format(ADDED_MOVIE, addMovieModel.getName()));

        return "redirect:/movies/all";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        MovieDTO movieDTO = this.movieService.getMovieByIdAndReturnDTO(id);

        checkIfIsNullAndAddAttribute(id, model, movieDTO);

        return "edit-movie";
    }

    @PostMapping("/{id}/edit")
    public String editMovie(@ModelAttribute("movie") MovieDTO movie, RedirectAttributes redirectAttributes) {
        Long id = movie.getId();
        this.movieService.editMovie(movie, id);

        addFlashAttribute(redirectAttributes, String.format(UPDATED_MOVIE_DETAILS, movie.getName()));

        return "redirect:/movies/all";

    }

    @GetMapping("/delete/{id}")
    public String getDeleteError(@PathVariable("id") Long id,Model model) {
        MovieDTO movieEntity = this.movieService.getMovieByIdAndReturnDTO(id);

        if (movieEntity == null) {
            throw new ObjectNotFoundException(id, "movieEntity");
        }

        model.addAttribute("itemId", id);
        model.addAttribute("itemType", movieEntity);

        return "redirect:/movies/all";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        this.movieService.deleteMovie(id);

        addFlashAttribute(redirectAttributes, DELETE_MOVIE);

        return "redirect:/movies/all";
    }

    private static void checkIfIsNullAndAddAttribute(Long id, Model model, MovieDTO movieDTO) {
        if (movieDTO == null) {
            throw new ObjectNotFoundException(id, "movieDTO");
        }
        model.addAttribute("itemId", id);
        model.addAttribute("itemType", movieDTO);

        model.addAttribute("movie", movieDTO);
    }

    private static void addFlashAttribute(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("successMessage",
                message);
    }
}
