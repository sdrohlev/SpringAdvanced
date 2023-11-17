package com.example.dorne.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event extends BaseEntity {

    private String name;
    private String description;
    private LocalDateTime dayAndTime;
    private String imgUrl;
    private Destination destination;

    public Event() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @FutureOrPresent
    @Column(nullable = false)
    public LocalDateTime getDayAndTime() {
        return dayAndTime;
    }

    public void setDayAndTime(LocalDateTime dayAndTime) {
        this.dayAndTime = dayAndTime;
    }

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @Column(nullable = false)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
