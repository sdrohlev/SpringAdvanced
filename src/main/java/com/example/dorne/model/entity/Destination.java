package com.example.dorne.model.entity;


import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Table
@Entity(name = "destinations")
public class Destination extends BaseEntity {

    private String name;
    private String imageUrl;
    private Set<Listing> listingsByDest;
    private Set<Event> eventsByDest;



    public Destination() {
    }

    public Destination(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.listingsByDest = new LinkedHashSet<>();
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<Listing> getListingsByDest() {
        return listingsByDest;
    }

    public void setListingsByDest(Set<Listing> listingsByDest) {
        this.listingsByDest = listingsByDest;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<Event> getEventsByDest() {
        return eventsByDest;
    }

    public void setEventsByDest(Set<Event> eventsByDest) {
        this.eventsByDest = eventsByDest;
    }


}
