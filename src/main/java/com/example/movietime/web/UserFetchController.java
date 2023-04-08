package com.example.movietime.web;

import com.example.movietime.domain.dtos.view.UserDTOFetch;
import com.example.movietime.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserFetchController {

    private final UserService userService;

    @Autowired
    public UserFetchController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDTOFetch> userList = userService.getAllUsersForFetch();
        model.addAttribute("userList", userList);
        return "displayUsers";
    }
}
