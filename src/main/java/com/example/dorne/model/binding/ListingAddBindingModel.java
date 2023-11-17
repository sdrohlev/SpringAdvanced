package com.example.dorne.model.binding;

import jakarta.validation.constraints.*;

public class ListingAddBindingModel {

    private String name;
    private double rating;
    private String imgUrl;
    private String destination;
    private String category;

    public ListingAddBindingModel() {
    }

    @NotBlank
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Min(value = 0, message = "Rating must be more than 0!")
    @Max(value = 10, message = "Rating must be less than 10!")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @NotBlank(message = "You must enter a valid URL link!")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @NotBlank(message = "You must select a destination!")
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @NotBlank(message = "You must select a category!")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
