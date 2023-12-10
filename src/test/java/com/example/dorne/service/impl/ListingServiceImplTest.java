package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Category;
import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.model.entity.enums.UserRoleEnum;
import com.example.dorne.model.service.ListingServiceModel;
import com.example.dorne.repository.CategoryRepository;
import com.example.dorne.repository.ListingRepository;
import com.example.dorne.service.CategoryService;
import com.example.dorne.service.DestinationService;
import com.example.dorne.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListingServiceImplTest {

    private ListingServiceImpl listingServiceImplToTest;
    @Mock
    private ListingRepository mockListingRepository;
    @Mock
    private CategoryService categoryService;
    @Mock
    private Destination destination;
    @Mock
    private DestinationService destinationService;
    private UserService userService;
    private ModelMapper modelMapper;



    @BeforeEach
    void setUp() {

        listingServiceImplToTest =
                new ListingServiceImpl(modelMapper, categoryService, destinationService, mockListingRepository, userService);
    }
    @Test
    void addListing() {

        //Arrange
        Destination destination = new Destination("Bansko", "https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png");
        ListingServiceModel listingServiceModel = new ListingServiceModel()
                .setName("ApartHotel Lucky Bansko")
                .setRating(7.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory("HOTEL")
                .setDestination("Bansko, Bulgaria");

        //Act
        //Assert
    }

    @Test
    void isContainsTest() {
        //Arrange
        ListingServiceModel listingServiceModel = new ListingServiceModel()
                .setName("ApartHotel Lucky Bansko")
                .setRating(7.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory("HOTEL")
                .setDestination("Bansko, Bulgaria");
        when(mockListingRepository.findByName(listingServiceModel.getName()))
                .thenReturn(Optional.of(new Listing()
                        .setName(listingServiceModel.getName())
                        .setImgUrl(listingServiceModel.getImgUrl())
                        .setRating(listingServiceModel.getRating())
                        .setCategory(new Category(CategoryNameEnum.HOTEL))
                        .setDestination(new Destination("Sofia, Bulgaria", "https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png"))
                        .setUser(new UserEntity()
                                .setUsername("test")
                                .setEmail("test@gmail.com")
                                .setPassword("test test")
                                .setRole(UserRoleEnum.USER))));
        //Assert
        Assertions.assertTrue(listingServiceImplToTest.isContains(listingServiceModel));
    }

    @Test
    void findByCategoryNameTest() {
        //Arrange
        Destination destination = new Destination("Bansko", "https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png");
        Listing listing = new Listing()
                .setName("ApartHotel Lucky Bansko")
                .setRating(7.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory(new Category(CategoryNameEnum.HOTEL))
                .setDestination(destination);


        when(mockListingRepository.findAllByCategoryName(CategoryNameEnum.HOTEL))
                .thenReturn(List.of(listing));

        //Act
        List<Listing> byCategoryName = listingServiceImplToTest.findByCategoryName(CategoryNameEnum.HOTEL);

        //Assert
        Assertions.assertEquals(byCategoryName.size(), 1);



    }

    @Test
    void findByIdTest() {
        //Arrange
        Listing listing = new Listing()
                .setName("ApartHotel Lucky Bansko")
                .setRating(7.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory(new Category(CategoryNameEnum.HOTEL))
                .setDestination(destination);
        when(mockListingRepository.findById(listing.getId())).thenReturn(Optional.of(listing));
        //Act
        Listing listingById = listingServiceImplToTest.findById(listing.getId());
        //Assert
        Assertions.assertNotNull(listingById);
    }

    @Test
    void findByUserIdTest() {
        //Arrange
        Listing listing = new Listing()
                .setName("ApartHotel Lucky Bansko")
                .setRating(7.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory(new Category(CategoryNameEnum.HOTEL))
                .setDestination(destination)
                .setUser(new UserEntity()
                        .setUsername("test")
                        .setPassword("1234")
                        .setEmail("test@gmail.com")
                        .setRole(UserRoleEnum.USER));

        when(mockListingRepository.findListingByUserId(listing.getUser().getId())).thenReturn(List.of(listing));
        //Act
        List<Listing> byUserId = listingServiceImplToTest.findByUserId(listing.getUser().getId());
        //Assert
        Assertions.assertEquals(byUserId.size(), 1);
    }

    @Test
    void addReviewToListingTest() {
        //Arrange
        Listing listing = new Listing()
                .setName("ApartHotel Lucky Bansko")
                .setRating(7.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory(new Category(CategoryNameEnum.HOTEL))
                .setDestination(destination);
        when(mockListingRepository.findById(listing.getId())).thenReturn(Optional.of(listing));

        //Act
        listingServiceImplToTest.addReviewToListing(listing.getId(), "Lets set a review to that listing!");
        //Assert
        Assertions.assertNotNull(listing.getReview());
    }

    @Test
    void removeListing() {
    }

    @Test
    void findByDestinationTest() {
        //Arrange
        Destination destination = new Destination("Bansko", "https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png");
        Listing listing1 = new Listing()
                .setName("ApartHotel Lucky Bansko")
                .setRating(7.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory(new Category(CategoryNameEnum.HOTEL))
                .setDestination(destination);
        Listing listing2 = new Listing()
                .setName("Resort Fly Away")
                .setRating(4.7)
                .setImgUrl("https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png")
                .setCategory(new Category(CategoryNameEnum.HOTEL))
                .setDestination(destination);
        when(mockListingRepository.findListingByDestinationId(destination.getId())).thenReturn(List.of(listing1, listing2));
        //Act
        List<Listing> listingByDestination = listingServiceImplToTest.findByDestination(destination.getId());
        //Assert
        Assertions.assertEquals(listingByDestination.size(), 2);
    }

    @Test
    void findAllByDestinationAndCategory() {
    }
}