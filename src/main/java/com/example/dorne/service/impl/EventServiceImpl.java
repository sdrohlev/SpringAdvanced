package com.example.dorne.service.impl;

import com.example.dorne.model.entity.Event;
import com.example.dorne.model.service.EventServiceModel;
import com.example.dorne.repository.EventRepository;
import com.example.dorne.service.DestinationService;
import com.example.dorne.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, DestinationService destinationService, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.destinationService = destinationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isContains(EventServiceModel eventServiceModel) {
        return this.eventRepository.findByName(eventServiceModel.getName()).isPresent();
    }

    @Override
    public void addEvent(EventServiceModel eventServiceModel) {

        Event event = this.modelMapper.map(eventServiceModel, Event.class);
        event.setDestination(this.destinationService.findByName(eventServiceModel.getDestination()));
        this.eventRepository.save(event);

    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }


}
