package com.example.movietime.services;

import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.model.MyListDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.entities.MyListEntity;

public interface MyListService {

    void addSeriesToMyListAndSave(MyListDTO myList, SeriesDTO series);
    void setMyListAndUser(UserDTO currentUser);
    void saveMyList(MyListEntity myList);
    void removeSeries(UserDTO currentUser, SeriesDTO seriesToRemove);
    void addMovieToMyListAndSave(MyListDTO myListDTO, MovieDTO movieDTO);
    void removeMovie(UserDTO currentUserDTO, MovieDTO movieToRemove);

    void saveForSchedule(MyListEntity myList);
}
