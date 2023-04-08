package com.example.movietime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MovieTimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieTimeApplication.class, args);
    }

}
