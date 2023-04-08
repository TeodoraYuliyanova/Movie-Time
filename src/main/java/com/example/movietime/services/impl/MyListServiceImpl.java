package com.example.movietime.services.impl;

import com.example.movietime.domain.dtos.model.MovieDTO;
import com.example.movietime.domain.dtos.model.MyListDTO;
import com.example.movietime.domain.dtos.model.SeriesDTO;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.entities.MovieEntity;
import com.example.movietime.domain.entities.MyListEntity;
import com.example.movietime.domain.entities.SeriesEntity;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.repositories.MyListRepository;
import com.example.movietime.services.MyListService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyListServiceImpl implements MyListService {

    private final MyListRepository myListRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public MyListServiceImpl(MyListRepository myListRepository, ModelMapper modelMapper) {
        this.myListRepository = myListRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addSeriesToMyListAndSave(MyListDTO myListDTO, SeriesDTO seriesDTO) {
        SeriesEntity seriesEntity = mapSeriesDTOToEntity(seriesDTO);
        MyListEntity myListEntity = mapMyListDTOToEntity(myListDTO);

        if (myListEntity == null || seriesEntity == null) {
            return;
        }

        List<SeriesEntity> seriesList = myListEntity.getSeries();
        if (seriesList == null) {
            seriesList = new ArrayList<>();
        }

        seriesList.add(seriesEntity);
        myListEntity.setSeries(seriesList);

        saveMyList(myListEntity);
        mapSeriesEntityToDTO(seriesEntity);
        mapMyListEntityToDTO(myListEntity);
    }
    @Override
    public void removeSeries(UserDTO currentUserDTO, SeriesDTO seriesToRemove) {
        MyListDTO myListDTO = currentUserDTO.getMyList();
        if (myListDTO != null) {
            List<SeriesDTO> seriesList = myListDTO.getSeries();
            if (seriesList != null && !seriesList.isEmpty()) {
                if (seriesList.removeIf(s -> s.getId().equals(seriesToRemove.getId()))) {
                    MyListEntity myListEntity = mapMyListDTOToEntity(myListDTO);
                    saveMyList(myListEntity);
                }
            }
        }

        mapUserDTOToUserEntity(currentUserDTO);
        mapSeriesDTOToEntity(seriesToRemove);
    }


    @Override
    public void addMovieToMyListAndSave(MyListDTO myListDTO, MovieDTO movieDTO) {
        MovieEntity movieEntity = this.modelMapper.map(movieDTO, MovieEntity.class);
        MyListEntity myListEntity = mapMyListDTOToEntity(myListDTO);

        if (myListEntity == null || movieEntity == null) {
            return;
        }

        List<MovieEntity> moviesList = myListEntity.getMovies();
        if (moviesList == null) {
            moviesList = new ArrayList<>();
        }

        moviesList.add(movieEntity);
        myListEntity.setMovies(moviesList);

        saveMyList(myListEntity);
        mapMovieEntityToDTO(movieEntity);
        mapMyListEntityToDTO(myListEntity);
    }

    @Override
    public void removeMovie(UserDTO currentUserDTO, MovieDTO movieToRemove) {
        MyListDTO myListDTO = currentUserDTO.getMyList();
        if (myListDTO != null) {
            List<MovieDTO> moviesList = myListDTO.getMovies();
            if (moviesList != null && !moviesList.isEmpty()) {
                if (moviesList.removeIf(m -> m.getId().equals(movieToRemove.getId()))) {
                    MyListEntity myListEntity = mapMyListDTOToEntity(myListDTO);
                    saveMyList(myListEntity);
                }
            }
        }

        mapUserDTOToUserEntity(currentUserDTO);
        this.modelMapper.map(movieToRemove,MovieEntity.class);
    }

    @Override
    public void setMyListAndUser(UserDTO currentUser) {
        MyListDTO myListDTO = currentUser.getMyList();

        if (myListDTO == null) {
            myListDTO = new MyListDTO();
            currentUser.setMyList(myListDTO);
            myListDTO.setUser(currentUser);
        }

        UserEntity userEntity = this.modelMapper.map(currentUser, UserEntity.class);
        MyListEntity myListEntity = mapMyListDTOToEntity(myListDTO);
        this.modelMapper.map(userEntity,UserDTO.class);
        mapMyListEntityToDTO(myListEntity);
    }

    @Override
    public void saveMyList(MyListEntity myList) {
        this.myListRepository.save(myList);
    }

    @Override
    public void saveForSchedule(MyListEntity myList) {
        this.myListRepository.save(myList);
    }

    private MyListEntity mapMyListDTOToEntity(MyListDTO myListDTO) {
        return this.modelMapper.map(myListDTO, MyListEntity.class);
    }

    private SeriesEntity mapSeriesDTOToEntity(SeriesDTO seriesDTO) {
        return this.modelMapper.map(seriesDTO, SeriesEntity.class);
    }

    private void mapUserDTOToUserEntity(UserDTO currentUserDTO) {
        this.modelMapper.map(currentUserDTO,UserEntity.class);
    }

    private void mapMovieEntityToDTO(MovieEntity movieEntity) {
        this.modelMapper.map(movieEntity,MovieDTO.class);
    }

    private void mapMyListEntityToDTO(MyListEntity myListEntity) {
        this.modelMapper.map(myListEntity,MyListDTO.class);
    }

    private void mapSeriesEntityToDTO(SeriesEntity seriesEntity) {
        this.modelMapper.map(seriesEntity,SeriesDTO.class);
    }

}
