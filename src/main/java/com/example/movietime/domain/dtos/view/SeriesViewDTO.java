package com.example.movietime.domain.dtos.view;

public class SeriesViewDTO {

    private Long id;
    private String name;
    private String description;
    private String videoUrl;
//    private String pictureUrl;

    public SeriesViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public SeriesViewDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SeriesViewDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SeriesViewDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public SeriesViewDTO setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
