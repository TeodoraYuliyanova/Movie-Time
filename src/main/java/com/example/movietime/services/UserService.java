package com.example.movietime.services;

import com.example.movietime.domain.dtos.binding.UserRegisterFormDto;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.dtos.model.UsersRestDTO;
import com.example.movietime.domain.dtos.view.UsersViewDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterFormDto userRegisterFormDto);

    UserDTO getUserByEmail(String email);

    void saveUser(UserDTO user);

    List<UsersViewDTO> getAllUsers();

    Optional<UserDTO> findByUsername(String username);

    List<UsersRestDTO> getAllUsersRest();

    void deleteUserREST(UserDTO user);

    UserDTO findById(Long id);

}
