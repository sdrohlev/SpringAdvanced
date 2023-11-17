package com.example.dorne.repository;

import com.example.dorne.model.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, String> {

    Optional<Listing> findByName(String listingName);


}
