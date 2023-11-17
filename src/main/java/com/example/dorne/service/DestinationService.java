package com.example.dorne.service;

import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.Listing;

public interface DestinationService {

    void addDestination(String name);

    Destination findByName(String destination);

    void addListingToDestination(Listing listing, String destinationName);
}
