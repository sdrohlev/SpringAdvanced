package com.example.dorne.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "listings")
public class Listing extends BaseEntity{

    private String name;
    private double rating;
    private double price;
    private String imgUrl;
    private String review;
    private Destination destination;
    private Category category;
    private UserEntity user;

    public Listing() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public Listing setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public double getRating() {
        return rating;
    }

    public Listing setRating(double rating) {
        this.rating = rating;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    public Destination getDestination() {
        return destination;
    }

    public Listing setDestination(Destination destination) {
        this.destination = destination;
        return this;
    }

    @Column(nullable = false)
    public String getImgUrl() {
        return imgUrl;
    }

    public Listing setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getReview() {
        return review;
    }

    public Listing setReview(String review) {
        this.review = review;
        return this;
    }

    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public Listing setCategory(Category category) {
        this.category = category;
        return this;
    }
    @ManyToOne
    @JoinColumn(nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public Listing setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
