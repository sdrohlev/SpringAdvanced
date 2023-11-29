package com.example.dorne.model.binding;

import com.example.dorne.model.entity.Destination;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public class EventAddBindingModel {

    private String name;
    private String description;
    private LocalDateTime dayAndTime;
    private String imgUrl;
    private String destination;

    public EventAddBindingModel() {
    }

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 3, max = 60, message = "Description length must be between 3 and 20 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @FutureOrPresent(message = "Please enter a valid date")
    public LocalDateTime getDayAndTime() {
        return dayAndTime;
    }

    public void setDayAndTime(LocalDateTime dayAndTime) {
        this.dayAndTime = dayAndTime;
    }

    @NotBlank(message = "Please enter a image url")
    @URL(message = "Please enter a valid image url")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @NotEmpty(message = "You must select a destination!")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
