package com.example.dorne.repository;

import com.example.dorne.model.entity.Category;
import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

    Optional<Listing> findByName(String listingName);
    List<Listing> findAllByCategoryName (CategoryNameEnum categoryNameEnum);
    List<Listing> findListingByUserId(String userId);
    List<Listing> findListingByDestinationId(String id);
    List<Listing> findAllByDestinationNameAndCategoryName(String destination, CategoryNameEnum categoryNameEnum);

}
