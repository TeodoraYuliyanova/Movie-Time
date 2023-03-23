package com.example.movietime.repositories;

import com.example.movietime.domain.entities.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<SeriesEntity,Long> {
}
