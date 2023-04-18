package com.example.movietime.recommendations;

import com.example.movietime.events.MovieAddedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MovieRecommendationEngine {

    @EventListener
    public void handleMovieAddedEvent(MovieAddedEvent movieAddedEvent) {
        System.out.printf("Movie: %s was added.",movieAddedEvent.getMovieEntity().getName());
    }
}
