package com.example.dorne.web;

import com.example.dorne.model.binding.EventAddBindingModel;
import com.example.dorne.model.service.EventServiceModel;
import com.example.dorne.model.service.EventServiceModelAPI;
import com.example.dorne.service.DestinationService;
import com.example.dorne.service.EventService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    public EventController(EventService eventService, DestinationService destinationService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.destinationService = destinationService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/all")
    public String allEvents(Model model) {

        //model.addAttribute("events", this.eventService.findAll());

        return "events";
    }
    @ResponseBody
    @GetMapping("/api/all")
    public ResponseEntity<List<EventServiceModelAPI>> getAllEvents(Model model) {

        //model.addAttribute("events", this.eventService.findAll());

        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @ResponseBody
    @GetMapping("/api/{id}")
    public ResponseEntity<EventServiceModel> findEventById (@PathVariable("id") String id) {

        EventServiceModel eventServiceModel = eventService.findEventById(id);
        return eventServiceModel != null
                ? ResponseEntity.ok(eventServiceModel)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/add")
    public String addDestination(Model model) {
        if (!model.containsAttribute("eventAddBindingModel")) {
            model.addAttribute("eventAddBindingModel", new EventAddBindingModel());
            model.addAttribute("destinations", this.destinationService.findAll());
        }

        return "event-add";
    }

    @PostMapping("/add")
    public String addDestinationConfirm(@Valid EventAddBindingModel eventAddBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) {

        if (this.eventService.isContains(this.modelMapper.map(eventAddBindingModel, EventServiceModel.class))) {
            redirectAttributes.addFlashAttribute("eventExist", true);
            return "redirect:add";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("eventAddBindingModel", eventAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventAddBindingModel", bindingResult);

            return "redirect:add";
        }

        eventService.addEvent(this.modelMapper.map(eventAddBindingModel, EventServiceModel.class));

        return "redirect:/";
    }


}
