package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.model.service.ListingServiceModel;
import com.example.dorne.repository.ListingRepository;
import com.example.dorne.repository.UserRepository;
import com.example.dorne.service.CategoryService;
import com.example.dorne.service.DestinationService;
import com.example.dorne.service.ListingService;
import com.example.dorne.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ListingServiceImpl implements ListingService {

    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final DestinationService destinationService;
    private final UserService userService;
    private final ListingRepository listingRepository;

    public ListingServiceImpl(ModelMapper modelMapper,
                              CategoryService categoryService,
                              DestinationService destinationService,
                              ListingRepository listingRepository,
                              UserService userService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.destinationService = destinationService;
        this.listingRepository = listingRepository;
        this.userService = userService;
    }

    @Override
    public void addListing(ListingServiceModel listingServiceModel) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        Listing listing = this.modelMapper.map(listingServiceModel, Listing.class);
        listing.setCategory(this.categoryService.findByName(listingServiceModel.getCategory()));
        listing.setDestination(this.destinationService.findByName(listingServiceModel.getDestination()));
        listing.setUser(this.userService.findByEmailAuth(currentUserEmail));
        this.listingRepository.save(listing);
        this.destinationService.addListingToDestination(listing, listingServiceModel.getDestination());
    }

    public boolean isContains(ListingServiceModel listingServiceModel) {
        return this.listingRepository.findByName(listingServiceModel.getName()).isPresent();
    }

    @Override
    public List<Listing> findByCategoryName(CategoryNameEnum categoryNameEnum) {
        return this.listingRepository.findAllByCategoryName(categoryNameEnum);
    }

    @Override
    public Listing findById(String id) {
        return this.listingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Listing> findByUserId(String userId) {
        return this.listingRepository.findListingByUserId(userId);
    }


}
