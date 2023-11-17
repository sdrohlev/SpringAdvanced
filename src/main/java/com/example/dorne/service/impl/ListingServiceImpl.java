package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.service.ListingServiceModel;
import com.example.dorne.repository.ListingRepository;
import com.example.dorne.service.CategoryService;
import com.example.dorne.service.DestinationService;
import com.example.dorne.service.ListingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ListingServiceImpl implements ListingService {

    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final DestinationService destinationService;
    private final ListingRepository listingRepository;

    public ListingServiceImpl(ModelMapper modelMapper, CategoryService categoryService, DestinationService destinationService, ListingRepository listingRepository) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.destinationService = destinationService;
        this.listingRepository = listingRepository;
    }

    @Override
    public void addListing(ListingServiceModel listingServiceModel) {

        Listing listing = this.modelMapper.map(listingServiceModel, Listing.class);
        listing.setCategory(this.categoryService.findByName(listingServiceModel.getCategory()));
        this.destinationService.addDestination(listingServiceModel.getDestination());
        listing.setDestination(this.destinationService.findByName(listingServiceModel.getDestination()));
        this.listingRepository.save(listing);
        this.destinationService.addListingToDestination(listing, listingServiceModel.getDestination());
    }

    public boolean isContains(ListingServiceModel listingServiceModel) {
        return this.listingRepository.findByName(listingServiceModel.getName()).isPresent();
    }



}
