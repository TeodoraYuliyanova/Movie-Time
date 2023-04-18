package com.example.movietime.web;

import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.dtos.view.UserProfileViewModel;
import com.example.movietime.services.ProfileService;
import com.example.movietime.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static com.example.movietime.outputs.SuccessMessages.EDIT_PROFILE;
import static com.example.movietime.web.RegisterController.BINDING_RESULT_PATH;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;

    @Autowired
    public ProfileController(UserService userService,ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @GetMapping
    private String viewProfile(Principal principal, Model model) {
        String email = principal.getName();
        UserDTO user = getUserByEmail(email);

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
        UserDTO user = getUserByEmail(email);
        model.addAttribute("user", user);
        return "edit-profile";
    }


    @PostMapping("/edit")
    public String saveProfile(@ModelAttribute("user") UserDTO userDTO, Principal principal, RedirectAttributes redirectAttributes) {
        String email = principal.getName();
        UserDTO existingUser = getUserByEmail(email);

        this.profileService.setAndSaveNewDetailsForUser(userDTO, existingUser.getEmail());

        redirectAttributes.addFlashAttribute("successMessage", EDIT_PROFILE);
        return "redirect:/";

    }
    private UserDTO getUserByEmail(String email) {
        return this.userService.getUserByEmail(email);
    }

}