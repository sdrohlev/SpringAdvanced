package com.example.dorne.service;

import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.model.service.ListingServiceModel;

import java.util.List;


public interface ListingService {
    void addListing(ListingServiceModel listingServiceModel);

    boolean isContains(ListingServiceModel listingServiceModel);

    List<Listing> findByCategoryName(CategoryNameEnum categoryNameEnum);

    Listing findById(String id);

    List<Listing> findByUserId(String userId);

    void addReviewToListing(String id, String review);

    void removeListing(String id);

    List<Listing> findByDestination(String id);

    List<Listing> findAllByDestinationAndCategory(String destination, String category);
}
