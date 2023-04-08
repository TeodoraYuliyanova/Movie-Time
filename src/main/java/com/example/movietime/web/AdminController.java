package com.example.movietime.web;

import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.dtos.view.UsersViewDTO;
import com.example.movietime.domain.entities.RoleEntity;
import com.example.movietime.domain.enums.RoleNameEnum;
import com.example.movietime.services.UserService;
import com.example.movietime.services.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

import static com.example.movietime.outputs.SuccessMessages.MAKE_USER_ADMIN;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/users/all")
    public String getAllUsers(Model model) {
        List<UsersViewDTO> users = this.userService.getAllUsers();

        model.addAttribute("users", users);
        return "all-users";
    }

    @PostMapping("/users/make-admin")
    public String makeAdmin(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {

        Optional<UserDTO> userOptional = this.userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            return "redirect:/admin/users/all";
        }

        UserDTO user = userOptional.get();
        if (user.getRoles().stream().anyMatch(roleEntity -> roleEntity.getRole() == RoleNameEnum.ADMIN)) {
            return "redirect:/admin/users/all";
        }

        RoleEntity adminRoleEntity = this.roleService.findRoleByRoleName(RoleNameEnum.ADMIN);
        user.getRoles().add(adminRoleEntity);
        this.userService.saveUser(user);

        redirectAttributes.addFlashAttribute("successMessage",
                String.format(MAKE_USER_ADMIN, user.getUsername()));
        return "redirect:/admin/users/all";
    }


}
