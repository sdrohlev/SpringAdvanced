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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonFormat(pattern="dd/MM HH:mm")
    public LocalDateTime getDayAndTime() {
        return dayAndTime;
    }

    public void setDayAndTime(LocalDateTime dayAndTime) {
        this.dayAndTime = dayAndTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
