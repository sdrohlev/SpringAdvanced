package com.example.dorne.model.service;

public class ListingServiceModel {

    private String name;
    private double rating;
    private String imgUrl;
    private String destination;
    private String category;

    public ListingServiceModel() {
    }

    public String getName() {
        return name;
    }

    public ListingServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public double getRating() {
        return rating;
    }

    public ListingServiceModel setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public ListingServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public ListingServiceModel setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public ListingServiceModel setCategory(String category) {
        this.category = category;
        return this;
    }
}
