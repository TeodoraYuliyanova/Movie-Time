package com.example.movietime.repositories;

import com.example.movietime.domain.entities.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<SeriesEntity,Long> {

}
