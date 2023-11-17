package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.Listing;
import com.example.dorne.repository.DestinationRepository;
import com.example.dorne.service.DestinationService;
import org.springframework.stereotype.Service;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }


    @Override
    public void addDestination(String destinationName) {
        if (this.destinationRepository.findByName(destinationName).isEmpty()) {
            Destination destination = new Destination(destinationName);
            this.destinationRepository.save(destination);
        }
    }

    @Override
    public Destination findByName(String destinationName) {
        return this.destinationRepository.findByName(destinationName).orElse(null);
    }

    @Override
    public void addListingToDestination(Listing listing, String destinationName) {
        this.destinationRepository.findByName(destinationName).get().getListingsByDest().add(listing);
    }
}
