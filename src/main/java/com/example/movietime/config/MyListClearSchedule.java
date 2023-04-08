package com.example.movietime.config;

import com.example.movietime.domain.entities.MovieEntity;
import com.example.movietime.domain.entities.MyListEntity;
import com.example.movietime.repositories.MyListRepository;
import com.example.movietime.services.MyListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

@Component
public class MyListClearSchedule {

    private final MyListRepository myListRepository;
    private final MyListService myListService;

    @Autowired
    public MyListClearSchedule(MyListRepository myListRepository, MyListService myListService) {
        this.myListRepository = myListRepository;
        this.myListService = myListService;
    }


    @Scheduled(cron = "0 0 0 * * ?") // running at midnight
    public void clearCartOffers() {
        List<MyListEntity> carts = this.myListRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (MyListEntity myList : carts) {

            if (myList.getMovies() != null) {
                Iterator<MovieEntity> iterator = myList.getMovies().iterator();
                while (iterator.hasNext()) {
                    MovieEntity movie = iterator.next();
                    iterator.remove();
                }
                this.myListService.saveForSchedule(myList);
            }
        }
    }
}
