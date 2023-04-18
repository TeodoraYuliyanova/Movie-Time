package com.example.movietime.repositories;

import com.example.movietime.domain.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Long> {

    Optional<GenreEntity> findById(Long id);

    Optional<GenreEntity> findGenreByName(String name);

}
