package com.example.movietime.web;

import com.example.movietime.domain.dtos.view.UserProfileViewModel;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.repositories.UserRepository;
import com.example.movietime.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private String viewProfile(Principal principal, Model model) {
        String email = principal.getName();
        UserEntity user = this.userService.getUserByEmail(email);

        UserProfileViewModel userProfileView = new UserProfileViewModel()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUsername(user.getUsername())
                .setEmail(email);

        model.addAttribute("user", userProfileView);

        return "profile";
    }

    @GetMapping("/edit")
    public String editProfile(Principal principal, Model model) {
        String email = principal.getName();
        UserEntity user = this.userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "edit-profile";
    }


    @PostMapping("/edit")
    public String saveProfile(@ModelAttribute("user") UserEntity user, Principal principal, RedirectAttributes redirectAttributes) {
        String email = principal.getName();
        UserEntity existingUser = this.userService.getUserByEmail(email);

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        this.userService.saveUser(existingUser);
        redirectAttributes.addFlashAttribute("successMessage", "User details updated successfully.");
        return "redirect:/";

    }

}