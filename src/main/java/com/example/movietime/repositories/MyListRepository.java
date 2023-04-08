package com.example.movietime.repositories;

import com.example.movietime.domain.entities.MyListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MyListRepository extends JpaRepository<MyListEntity , Long> {
}
