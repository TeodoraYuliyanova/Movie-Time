package com.example.movietime.events;

import com.example.movietime.domain.entities.MovieEntity;
import org.springframework.context.ApplicationEvent;

public class MovieAddedEvent extends ApplicationEvent {

    private MovieEntity movieEntity;

    public MovieAddedEvent(Object source, MovieEntity movieEntity) {
        super(source);
        this.movieEntity = movieEntity;
    }

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }
}
