package com.example.dorne.model.binding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingAddBindingModelTest {

    ListingAddBindingModel listingAddBindingModelToTest = new ListingAddBindingModel();

    @Test
    void getName() {
        listingAddBindingModelToTest.getName();
    }

    @Test
    void setName() {
        listingAddBindingModelToTest.setName("NewName");
    }

    @Test
    void getRating() {
        listingAddBindingModelToTest.getRating();
    }

    @Test
    void setRating() {
        listingAddBindingModelToTest.setRating(1.1);
    }

    @Test
    void getImgUrl() {
        listingAddBindingModelToTest.getImgUrl();
    }

    @Test
    void setImgUrl() {
        listingAddBindingModelToTest.setImgUrl("ImgUrl");
    }

    @Test
    void getDestination() {
        listingAddBindingModelToTest.getDestination();
    }

    @Test
    void setDestination() {
        listingAddBindingModelToTest.setDestination("Destination");
    }

    @Test
    void getCategory() {
        listingAddBindingModelToTest.getCategory();
    }

    @Test
    void setCategory() {
        listingAddBindingModelToTest.setCategory("Category");
    }
}