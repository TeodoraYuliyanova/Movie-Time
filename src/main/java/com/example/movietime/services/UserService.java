package com.example.movietime.services;

import com.example.movietime.domain.dtos.banding.UserLoginFormDTO;
import com.example.movietime.domain.dtos.banding.UserRegisterFormDto;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.entities.UserEntity;

public interface UserService {

    void registerUser(UserRegisterFormDto userRegisterFormDto);

    UserEntity getUser(String username);

    UserEntity getUserByEmail(String email);

    void saveUser(UserEntity user);
}
