package com.example.dorne.service;

import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.service.DestinationServiceModel;
import com.example.dorne.model.service.ListingServiceModel;

import java.util.List;

public interface DestinationService {

    void addDestination(DestinationServiceModel destinationServiceModel);

    Destination findByName(String destination);

    void addListingToDestination(Listing listing, String destinationName);

    List<Destination> findAll();

    boolean isContains(DestinationServiceModel destinationServiceModel);

    Destination findById(String id);
}
