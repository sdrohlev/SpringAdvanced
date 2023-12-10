package com.example.dorne.model.binding;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventAddBindingModelTest {

    EventAddBindingModel eventAddBindingModelToTest = new EventAddBindingModel();

    @Test
    void getName() {
        eventAddBindingModelToTest.getName();
    }

    @Test
    void setName() {
        eventAddBindingModelToTest.setName("NewName");
    }

    @Test
    void getDescription() {
        eventAddBindingModelToTest.getDescription();
    }

    @Test
    void setDescription() {
        eventAddBindingModelToTest.setDescription("NewDesc");
    }

    @Test
    void getDayAndTime() {
        eventAddBindingModelToTest.getDayAndTime();
    }

    @Test
    void setDayAndTime() {
        eventAddBindingModelToTest.setDayAndTime(LocalDateTime.now());
    }

    @Test
    void getImgUrl() {
        eventAddBindingModelToTest.getImgUrl();
    }

    @Test
    void setImgUrl() {
        eventAddBindingModelToTest.setImgUrl("newImageUrl");
    }

    @Test
    void getDestination() {
        eventAddBindingModelToTest.getDestination();
    }

    @Test
    void setDestination() {
        eventAddBindingModelToTest.setDestination("Destination, Country");
    }
}