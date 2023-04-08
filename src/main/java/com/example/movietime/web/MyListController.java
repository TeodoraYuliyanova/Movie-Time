package com.example.movietime.web;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.model.MyListDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.services.MovieService;
import com.example.movietime.services.MyListService;
import com.example.movietime.services.SeriesService;
import com.example.movietime.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.example.movietime.outputs.SuccessMessages.*;

@Controller
@RequestMapping
public class MyListController {

    private final UserService userService;
    private final SeriesService seriesService;
    private final MyListService myListService;
    private final MovieService movieService;


    @Autowired
    public MyListController(UserService userService, SeriesService seriesService,
                            MyListService myListService, MovieService movieService) {
        this.userService = userService;
        this.seriesService = seriesService;
        this.myListService = myListService;
        this.movieService = movieService;
    }

    @GetMapping("/myList")
    public String getMyList(Model model, Principal principal) {

        UserDTO currentUser = getCurrentUser(principal);

        MyListDTO myList = currentUser.getMyList();
        List<SeriesDTO> seriesList = new ArrayList<>();
        List<MovieDTO> movieList = new ArrayList<>();

        if (myList != null) {
            seriesList = myList.getSeries();
            movieList = myList.getMovies();
        }

        model.addAttribute("seriesList", seriesList);
        model.addAttribute("movieList", movieList);
        return "myList";
    }


    @PostMapping("/myList/add-series/{id}")
    public String addSeriesToList(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes,
                                  Model model) {

        UserDTO currentUser = getCurrentUser(principal);
        SeriesDTO seriesToAdd = getSeriesById(id);

        checkIfSeriesIsNullAndAddAttribute(id, model, seriesToAdd);

        this.myListService.setMyListAndUser(currentUser);

        this.myListService.addSeriesToMyListAndSave(currentUser.getMyList(), seriesToAdd);

        addFlashAttribute(redirectAttributes, ADDED_SERIES_TO_MY_LIST, seriesToAdd.getName());

        return "redirect:/myList";
    }


    @PostMapping("/myList/remove-series/{id}")
    public String removeSeriesFromList(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes,
                                       Model model) {
        UserDTO currentUser = getCurrentUser(principal);
        SeriesDTO seriesToRemove = getSeriesById(id);

        checkIfSeriesIsNullAndAddAttribute(id, model, seriesToRemove);

        this.myListService.removeSeries(currentUser, seriesToRemove);

        addFlashAttribute(redirectAttributes, REMOVE_SERIES_FROM_MY_LIST, seriesToRemove.getName());

        return "redirect:/myList";
    }


    @PostMapping("/myList/add-movie/{id}")
    public String addMoviesToList(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes,
                                  Model model) {
        UserDTO currentUser = getCurrentUser(principal);
        MovieDTO movieToAdd = getMovieById(id);

        checkIfMovieIsNullAndAddAttribute(id, model, movieToAdd);

        this.myListService.setMyListAndUser(currentUser);

        this.myListService.addMovieToMyListAndSave(currentUser.getMyList(), movieToAdd);

        addFlashAttribute(redirectAttributes, ADDED_MOVIE_TO_MY_LIST, movieToAdd.getName());

        return "redirect:/myList";
    }


    @PostMapping("/myList/remove-movie/{id}")
    public String removeMoviesFromList(@PathVariable("id") Long id, Principal principal, RedirectAttributes redirectAttributes,
                                       Model model) {
        UserDTO currentUser = getCurrentUser(principal);
        MovieDTO movieToRemove = getMovieById(id);

        checkIfMovieIsNullAndAddAttribute(id, model, movieToRemove);

        this.myListService.removeMovie(currentUser, movieToRemove);

        addFlashAttribute(redirectAttributes, REMOVE_MOVIE_FROM_MY_LIST, movieToRemove.getName());

        return "redirect:/myList";
    }

    private UserDTO getCurrentUser(Principal principal) {
        String currentUserEmail = principal.getName();
        return this.userService.getUserByEmail(currentUserEmail);
    }

    private SeriesDTO getSeriesById(Long id) {
        return this.seriesService.getSeriesById(id);
    }

    private static void checkIfSeriesIsNullAndAddAttribute(Long id, Model model, SeriesDTO seriesDTO) {
        if (seriesDTO == null) {
            throw new ObjectNotFoundException(id, "series");
        }
        model.addAttribute("itemId", id);
        model.addAttribute("itemType", seriesDTO);
    }

    private static void addFlashAttribute(RedirectAttributes redirectAttributes, String message, String name) {
        redirectAttributes.addFlashAttribute("successMessage",
                String.format(message, name));
    }

    private static void checkIfMovieIsNullAndAddAttribute(Long id, Model model, MovieDTO movie) {
        if (movie == null) {
            throw new ObjectNotFoundException(id, "movie");
        }
        model.addAttribute("itemId", id);
        model.addAttribute("itemType", movie);
    }

    private MovieDTO getMovieById(Long id) {
        return this.movieService.getMovieByIdAndReturnDTO(id);
    }

}
