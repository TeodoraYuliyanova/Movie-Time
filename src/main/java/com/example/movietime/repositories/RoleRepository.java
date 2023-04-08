package com.example.movietime.repositories;

import com.example.movietime.domain.entities.RoleEntity;
import com.example.movietime.domain.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {

    List<RoleEntity> findByRole(RoleNameEnum role);
}
