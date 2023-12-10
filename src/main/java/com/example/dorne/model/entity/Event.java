package com.example.dorne.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

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

    public Event setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @FutureOrPresent
    @Column(nullable = false)
    public LocalDateTime getDayAndTime() {
        return dayAndTime;
    }

    public Event setDayAndTime(LocalDateTime dayAndTime) {
        this.dayAndTime = dayAndTime;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    public Destination getDestination() {
        return destination;
    }

    public Event setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    @Column(nullable = false)
    public String getImgUrl() {
        return imgUrl;
    }

    public Event setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
