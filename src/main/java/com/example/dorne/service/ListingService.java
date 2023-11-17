package com.example.dorne.service;

import com.example.dorne.model.service.ListingServiceModel;


public interface ListingService {
    void addListing(ListingServiceModel listingServiceModel);

    public boolean isContains(ListingServiceModel listingServiceModel);
}
