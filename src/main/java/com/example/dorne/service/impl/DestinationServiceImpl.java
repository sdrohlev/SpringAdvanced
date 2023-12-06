package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.service.DestinationServiceModel;
import com.example.dorne.repository.DestinationRepository;
import com.example.dorne.service.DestinationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }


    @Override
    public void addDestination(DestinationServiceModel destinationServiceModel) {
        if (this.destinationRepository.findByName(destinationServiceModel.getName()).isEmpty()) {
            Destination destination = new Destination(destinationServiceModel.getName(), destinationServiceModel.getImageUrl());
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

    @Override
    public List<Destination> findAll() {
        return this.destinationRepository.findAll();
    }

    @Override
    public boolean isContains(DestinationServiceModel destinationServiceModel) {
        return this.destinationRepository.findByName(destinationServiceModel.getName()).isPresent();
    }

    @Override
    public Destination findById(String id) {
        return this.destinationRepository.findById(id).orElse(null);
    }
}
