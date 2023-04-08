package com.example.movietime.web;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.CreateOrUpdateSeriesDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.dtos.view.AllSeriesViewDTO;
import com.example.movietime.services.SeriesService;
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
@RequestMapping("/series")
public class SeriesController {

    private final SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/all")
    public String getSeries(Model model) {
        List<AllSeriesViewDTO> seriesViewDTOS = this.seriesService.getAllSeries();

        model.addAttribute("series", seriesViewDTOS);
        return "all-series";
    }

    @GetMapping("/{id}")
    public String getSeriesDetail(@PathVariable("id") Long id,
                                  Model model) {

        SeriesDTO seriesDTO = getSeriesById(id);

        checkIfIsNullAndAddAttribute(id, model, seriesDTO);

        return "details-series";
    }

    @GetMapping("/add")
    public String addSeries(Model model) {
        if (!model.containsAttribute("addSeriesModel")) {
            model.addAttribute("addSeriesModel", new CreateOrUpdateSeriesDTO());
        }
        List<String> genres = Arrays.asList
                ("Action", "Crime", "Adventure", "Comedy", "Drama", "Fantasy",
                        "Horror", "Mystery", "Romance", "Science Fiction", "Thriller");
        model.addAttribute("genres", genres);

        return "add-series";
    }


    @PostMapping("/add")
    public String addSeries(@Valid CreateOrUpdateSeriesDTO addSeriesModel,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addSeriesModel", addSeriesModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSeriesModel",
                    bindingResult);
            return "redirect:/series/add";
        }

        addSeriesModel.setGenres(Arrays.asList(request.getParameterValues("genres")));

        this.seriesService.addSeries(addSeriesModel);

        addFlashAttribute(redirectAttributes, String.format(ADDED_SERIES, addSeriesModel.getName()));

        return "redirect:/series/all";
    }


    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       Model model) {
        SeriesDTO seriesDTO = getSeriesById(id);

        checkIfIsNullAndAddAttribute(id, model, seriesDTO);

        return "edit-series";
    }


    @PostMapping("/{id}/edit")
    public String editSeries(@ModelAttribute("series") SeriesDTO series, RedirectAttributes redirectAttributes) {
        Long id = series.getId();

        this.seriesService.editSeries(series, id);

        addFlashAttribute(redirectAttributes, String.format(UPDATED_SERIES_DETAILS, series.getName()));

        return "redirect:/series/all";

    }

    @GetMapping("delete/{id}")
    public String getDeleteError(@PathVariable("id") Long id, Model model) {
        SeriesDTO series = getSeriesById(id);
        if (series == null) {
            throw new ObjectNotFoundException(id, "series");
        }

        model.addAttribute("itemId", id);
        model.addAttribute("itemType", series);

        return "redirect:/series/all";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, Model model) {

        this.seriesService.deleteSeries(id);

        addFlashAttribute(redirectAttributes, DELETE_SERIES);

        return "redirect:/series/all";
    }

    private SeriesDTO getSeriesById(Long id) {
        return this.seriesService.getSeriesById(id);
    }


    private static void checkIfIsNullAndAddAttribute(Long id, Model model, SeriesDTO seriesDTO) {
        if (seriesDTO == null) {
            throw new ObjectNotFoundException(id, "series");
        }

        model.addAttribute("itemId", id);
        model.addAttribute("itemType", seriesDTO);


        model.addAttribute("series", seriesDTO);
    }

    private static void addFlashAttribute(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("successMessage",
                message);
    }
}
