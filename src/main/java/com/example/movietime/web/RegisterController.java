package com.example.movietime.web;

import com.example.movietime.domain.dtos.binding.UserRegisterFormDto;
import com.example.movietime.repositories.UserRepository;
import com.example.movietime.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.movietime.outputs.SuccessMessages.SUCCESSFUL_REGISTER;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private final UserService userService;
    private final UserRepository userRepository;

    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";


    @Autowired
    public RegisterController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute(name = "userRegisterFormDto") UserRegisterFormDto userRegisterFormDto,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterFormDto.getPassword().equals(userRegisterFormDto.getConfirmPassword()) ||
                this.userRepository.findUserEntityByEmail(userRegisterFormDto.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("userRegisterFormDto", userRegisterFormDto)
                    .addFlashAttribute(BINDING_RESULT_PATH + "userRegisterFormDto", bindingResult);

            return "redirect:register";
        }

        this.userService.registerUser(userRegisterFormDto);
        redirectAttributes.addFlashAttribute("successMessage",SUCCESSFUL_REGISTER);
        return "redirect:/users/login";
    }


    @ModelAttribute(name = "userRegisterFormDto")
    public UserRegisterFormDto userRegisterFormDto() {
        return new UserRegisterFormDto();
    }
}
