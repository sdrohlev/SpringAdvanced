package com.example.dorne.model.service;

import com.example.dorne.model.entity.Destination;

import java.time.LocalDateTime;

public class EventServiceModel {

    private String name;
    private String description;
    private LocalDateTime dayAndTime;
    private String imgUrl;
    private String destination;

    public EventServiceModel() {
    }

    public String getName() {
        return name;
    }

    public EventServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getDayAndTime() {
        return dayAndTime;
    }

    public EventServiceModel setDayAndTime(LocalDateTime dayAndTime) {
        this.dayAndTime = dayAndTime;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public EventServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public EventServiceModel setDestination(String destination) {
        this.destination = destination;
        return this;
    }
}
