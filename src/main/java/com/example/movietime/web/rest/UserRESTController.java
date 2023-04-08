package com.example.movietime.web.rest;


import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.dtos.model.UsersRestDTO;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {

    private final UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UsersRestDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsersRest());
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){

        UserDTO user = this.userService.findById(id);

        if (user == null) {
            throw new ObjectNotFoundException(id, "user");
        }

        this.userService.deleteUserREST(user);
        return ResponseEntity.ok(null);
    }
}
