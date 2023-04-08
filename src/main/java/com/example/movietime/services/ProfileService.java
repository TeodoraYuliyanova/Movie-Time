package com.example.movietime.services;

import com.example.movietime.domain.dtos.model.UserDTO;

public interface ProfileService {

    void setAndSaveNewDetailsForUser(UserDTO user, String email);
}
