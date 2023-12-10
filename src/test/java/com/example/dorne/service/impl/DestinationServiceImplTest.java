package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.model.service.DestinationServiceModel;
import com.example.dorne.repository.DestinationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DestinationServiceImplTest {

    DestinationServiceImpl destinationServiceToTest;
    @Mock
    DestinationRepository mockDestinationRepository;

    @BeforeEach
    void setUp() {

        destinationServiceToTest =
                new DestinationServiceImpl(mockDestinationRepository);
    }

    @Test
    void addDestination() {
        //Arrange
        DestinationServiceModel destinationServiceModel = new DestinationServiceModel()
                .setName("Sofia, Bulgaria")
                .setImageUrl("https://media.tacdn.com/media/attractions-content--1x-1/10/7d/eb/99.jpg");
        //Destination destination = new Destination("Bansko", "https://luckybansko.com/wp-content/uploads/2017/04/Detska_ploshtadka_big_14-1.png");
        //when(mockDestinationRepository.findByName(destination.getName()))
        //        .thenReturn(Optional.of(destination));
        //Act
        destinationServiceToTest.addDestination(destinationServiceModel);
        //Assert
        Mockito.verify(mockDestinationRepository).save(any());
    }

    @Test
    void findByName() {
    }

    @Test
    void addListingToDestination() {
    }

    @Test
    void findAll() {
    }

    @Test
    void isContains() {
    }

    @Test
    void findById() {
    }
}