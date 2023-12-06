package com.example.dorne.service;

import com.example.dorne.model.entity.Event;
import com.example.dorne.model.service.DestinationServiceModel;
import com.example.dorne.model.service.EventServiceModel;
import com.example.dorne.model.service.EventServiceModelAPI;

import java.util.List;
import java.util.Optional;

public interface EventService {

    boolean isContains(EventServiceModel eventServiceModel);

    void addEvent(EventServiceModel eventServiceModel);

    List<EventServiceModelAPI> getAllEvents();

    EventServiceModel findEventById(String id);

    void expiredEventsCleanUp();
}
