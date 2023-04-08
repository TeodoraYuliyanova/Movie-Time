package com.example.movietime.services.impl;

import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.services.ProfileService;
import com.example.movietime.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;
    private final ModelMapper modelMapper;


    @Autowired
    public ProfileServiceImpl(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void setAndSaveNewDetailsForUser(UserDTO userDTO,String email) {
        UserDTO existingUserDTO = this.userService.getUserByEmail(email);

        UserEntity userEntity = this.modelMapper.map(existingUserDTO, UserEntity.class);
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setEmail(userDTO.getEmail());

        UserDTO mappedExistingUser = this.modelMapper.map(userEntity, UserDTO.class);
        this.userService.saveUser(mappedExistingUser);
    }
}
