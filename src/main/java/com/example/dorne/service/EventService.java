package com.example.dorne.service;

import com.example.dorne.model.entity.Event;
import com.example.dorne.model.service.DestinationServiceModel;
import com.example.dorne.model.service.EventServiceModel;

import java.util.List;

public interface EventService {

    boolean isContains(EventServiceModel eventServiceModel);


    void addEvent(EventServiceModel eventServiceModel);


    List<Event> findAll();
}
