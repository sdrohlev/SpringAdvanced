package com.example.dorne.model.service;

import com.example.dorne.model.entity.Destination;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventServiceModelAPI {

    private String name;
    private String description;
    private LocalDateTime dayAndTime;
    private String imgUrl;
    private Destination destination;

    public EventServiceModelAPI() {
    }

    public String getName() {
        return name;
    }

    public EventServiceModelAPI setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EventServiceModelAPI setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonFormat(pattern="dd/MM HH:mm")
    public LocalDateTime getDayAndTime() {
        return dayAndTime;
    }

    public EventServiceModelAPI setDayAndTime(LocalDateTime dayAndTime) {
        this.dayAndTime = dayAndTime;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public EventServiceModelAPI setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public EventServiceModelAPI setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

}
