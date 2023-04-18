package com.example.movietime.repositories;

import com.example.movietime.domain.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
    List<MovieEntity> findByNameContainingIgnoreCase(String query);
}
