package com.example.movietime.services.impl;

import com.example.movietime.domain.ObjectNotFoundException;
import com.example.movietime.domain.dtos.binding.UserRegisterFormDto;
import com.example.movietime.domain.dtos.model.UserDTO;
import com.example.movietime.domain.dtos.model.UserRoleDTO;
import com.example.movietime.domain.dtos.model.UsersRestDTO;
import com.example.movietime.domain.dtos.view.UsersViewDTO;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, DataBaseInitService {

    private static final String USER_NOT_FOUND_BY_ID = "User with id: %s was not found";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
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

        saveUser(admin);
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

        saveUser(userEntity);

    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = this.userRepository.findUserEntityByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(email + " was not found!"));

        return mapUserEntityToDTO(userEntity);
    }

    @Override
    public void saveUser(UserDTO user) {
        UserEntity mappedUser = mapUserDTOToEntity(user);
        this.userRepository.save(mappedUser);
        mapUserEntityToDTO(mappedUser);
    }

    @Override
    public List<UsersViewDTO> getAllUsers() {
        return
                this.userRepository.
                        findAll()
                        .stream()
                        .map(this::map).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username)
                .map(this::mapUserEntityToDTO);
    }

    @Override
    public List<UsersRestDTO> getAllUsersRest() {
        List<UserEntity> allUsers = this.userRepository.findAll().stream().toList();

        return allUsers.stream().map(user -> this.modelMapper.map(user, UsersRestDTO.class)).toList();
    }

    @Override
    public void deleteUserREST(UserDTO user) {
        UserEntity userEntity = mapUserDTOToEntity(user);
        this.userRepository.delete(userEntity);
    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "user"));
        return mapUserEntityToDTO(userEntity);
    }

    private UsersViewDTO map(UserEntity userEntity) {
        return new UsersViewDTO()
                .setUsername(userEntity.getUsername())
                .setRoles(userEntity.getRoles()
                        .stream()
                        .map(roleEntity -> this.modelMapper.map(roleEntity, UserRoleDTO.class))
                        .collect(Collectors.toList()));

    }

    private UserEntity mapUserDTOToEntity(UserDTO user) {
        return this.modelMapper.map(user, UserEntity.class);
    }

    private UserDTO mapUserEntityToDTO(UserEntity userEntity) {
        return this.modelMapper.map(userEntity, UserDTO.class);
    }

    private void saveUser(UserEntity admin) {
        this.userRepository.saveAndFlush(admin);
    }
}
