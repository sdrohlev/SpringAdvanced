package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Destination;
import com.example.dorne.model.entity.Event;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.model.service.EventServiceModel;
import com.example.dorne.model.service.EventServiceModelAPI;
import com.example.dorne.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    EventServiceImpl eventServiceToTest;
    @Mock
    EventRepository mockEventRepository;
    @Mock
    DestinationServiceImpl mockDestinationService;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        eventServiceToTest =
                new EventServiceImpl(mockEventRepository, mockDestinationService, modelMapper);
    }


    @Test
    void isContainsTest() {
        //Arrange
        EventServiceModel eventServiceModel = new EventServiceModel()
                .setName("New Year Party")
                .setImgUrl("https://media.tacdn.com/media/attractions-content--1x-1/10/7d/eb/99.jpg")
                .setDayAndTime(LocalDateTime.now())
                .setDescription("party for new 2024")
                .setDestination("Sofia, Bulgaria");


        when(mockEventRepository.findByName(eventServiceModel.getName()))
                .thenReturn(Optional.of(new Event()
                                .setName(eventServiceModel.getName())
                                .setImgUrl(eventServiceModel.getImgUrl())
                                .setDestination(new Destination("Sofia, Bulgaria", "https://media.tacdn.com/media/attractions-content--1x-1/10/7d/eb/99.jpg"))
                                .setDescription(eventServiceModel.getDescription())
                                .setDayAndTime(eventServiceModel.getDayAndTime())
                        ));

        //Act

        //Assert
        Assertions.assertTrue(eventServiceToTest.isContains(eventServiceModel));
    }

    @Test
    void addEventTest() {
        //Arrange
        EventServiceModel eventServiceModel = new EventServiceModel()
                .setName("New Year Party")
                .setImgUrl("https://media.tacdn.com/media/attractions-content--1x-1/10/7d/eb/99.jpg")
                .setDayAndTime(LocalDateTime.now())
                .setDescription("party for new 2024")
                .setDestination("Sofia, Bulgaria");
        when(modelMapper.map(eventServiceModel, Event.class)).thenReturn(new Event()
                .setName(eventServiceModel.getName())
                .setImgUrl(eventServiceModel.getImgUrl())
                .setDestination(new Destination("Sofia, Bulgaria", "https://media.tacdn.com/media/attractions-content--1x-1/10/7d/eb/99.jpg"))
                .setDescription(eventServiceModel.getDescription())
                .setDayAndTime(eventServiceModel.getDayAndTime()));

        //Act
        eventServiceToTest.addEvent(eventServiceModel);

        //Assert
        Mockito.verify(mockEventRepository).save(any());

    }

    @Test
    void getAllEventsTest() {
        //Arrange
        Event event = new Event()
                .setName("New Event")
                .setImgUrl("https://media.tacdn.com/media/attractions-content--1x-1/10/7d/eb/99.jpg")
                .setDestination(new Destination("Sofia, Bulgaria", "https://media.tacdn.com/media/attractions-content--1x-1/10/7d/eb/99.jpg"))
                .setDescription("This is a description for the event!")
                .setDayAndTime(LocalDateTime.now());

        when(mockEventRepository.findAllByOrderByDayAndTimeAsc()).thenReturn(List.of(event));
        when(modelMapper.map(event, EventServiceModelAPI.class)).thenReturn(new EventServiceModelAPI()
                .setName(event.getName())
                .setImgUrl(event.getImgUrl())
                .setDescription(event.getDescription())
                .setDayAndTime(event.getDayAndTime())
                .setDestination(event.getDestination()));
        //Act
        List<EventServiceModelAPI> allEvents = eventServiceToTest.getAllEvents();
        //Assert
        Assertions.assertEquals(allEvents.size(), 1);
    }

    @Test
    void findEventById() {
    }

    @Test
    void expiredEventsCleanUp() {
    }
}