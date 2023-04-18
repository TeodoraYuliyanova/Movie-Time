package com.example.movietime.services.impl;

import com.example.movietime.domain.dtos.model.UserRoleDTO;
import com.example.movietime.domain.entities.RoleEntity;
import com.example.movietime.domain.enums.RoleNameEnum;
import com.example.movietime.repositories.RoleRepository;
import com.example.movietime.services.DataBaseInitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(0)
public class RoleServiceImpl implements DataBaseInitService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.dbInit();
    }

    @Override
    public void dbInit() {
     if (isDbInit()){
         List<RoleEntity> roles = new ArrayList<>();

         roles.add(new RoleEntity().setRole(RoleNameEnum.USER));
         roles.add(new RoleEntity().setRole(RoleNameEnum.ADMIN));

         this.roleRepository.saveAllAndFlush(roles);
     }
    }

    @Override
    public boolean isDbInit() {
        return this.roleRepository.count() == 0;
    }



    public RoleEntity findRoleByRoleName(RoleNameEnum admin) {
        return  this.roleRepository.findByRole(admin).get(0);
    }
}
