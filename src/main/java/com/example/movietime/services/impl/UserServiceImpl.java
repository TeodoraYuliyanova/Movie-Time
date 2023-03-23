package com.example.movietime.services.impl;

import com.example.movietime.domain.dtos.banding.UserRegisterFormDto;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.entities.UserEntity;
import com.example.movietime.domain.enums.RoleNameEnum;
import com.example.movietime.repositories.RoleRepository;
import com.example.movietime.repositories.UserRepository;
import com.example.movietime.services.DataBaseInitService;
import com.example.movietime.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, DataBaseInitService {

    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Override
    public boolean isDbInit() {
        return this.userRepository.count() == 0;
    }

    @Override
    public void dbInit() {
        UserEntity admin = new UserEntity().
                setFirstName("Admin").
                setLastName("Adminov").
                setEmail("admin@example.com").
                setUsername("admin").
                setPassword(passwordEncoder.encode("secret")).
                setRoles(this.roleRepository.findAll());

        this.userRepository.saveAndFlush(admin);
    }

    @Override
    public void registerUser(UserRegisterFormDto userRegisterFormDto) {
        if (isDbInit()) {
            dbInit();
        }

        UserEntity userEntity = new UserEntity()
                .setFirstName(userRegisterFormDto.getFirstName())
                .setLastName(userRegisterFormDto.getLastName())
                .setEmail(userRegisterFormDto.getEmail())
                .setRoles(this.roleRepository.findByRole(RoleNameEnum.USER))
                .setUsername(userRegisterFormDto.getUsername())
                .setPassword(passwordEncoder.encode(userRegisterFormDto.getPassword()));

        this.userRepository.saveAndFlush(userEntity);

    }

    @Override
    public UserEntity getUser(String username) {
        return this.userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findUserEntityByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(email + " was not found!"));
    }

    @Override
    public void saveUser(UserEntity user) {
        this.userRepository.save(user);
    }


}
