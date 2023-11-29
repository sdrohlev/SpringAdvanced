package com.example.dorne.model.service;

import com.example.dorne.model.entity.Event;
import com.example.dorne.model.entity.Listing;

import java.util.Set;

public class DestinationServiceModel {

    private String name;
    private String imageUrl;
    private Set<Listing> listingsByDest;
    private Set<Event> eventsByDest;

    public DestinationServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Listing> getListingsByDest() {
        return listingsByDest;
    }

    public void setListingsByDest(Set<Listing> listingsByDest) {
        this.listingsByDest = listingsByDest;
    }

    public Set<Event> getEventsByDest() {
        return eventsByDest;
    }

    public void setEventsByDest(Set<Event> eventsByDest) {
        this.eventsByDest = eventsByDest;
    }
}
