package com.example.dorne.web;

import com.example.dorne.model.binding.DestinationAddBindingModel;
import com.example.dorne.model.binding.ListingAddBindingModel;
import com.example.dorne.model.service.DestinationServiceModel;
import com.example.dorne.model.service.ListingServiceModel;
import com.example.dorne.repository.DestinationRepository;
import com.example.dorne.service.DestinationService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;
    private final ModelMapper modelMapper;

    public DestinationController(DestinationService destinationService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allEvents(Model model) {

        model.addAttribute("destinations", this.destinationService.findAll());

        return "destinations";
    }

    @GetMapping("/add")
    public String addDestination(Model model) {
        if (!model.containsAttribute("destinationAddBindingModel")) {
            model.addAttribute("destinationAddBindingModel", new DestinationAddBindingModel());
        }

        return "destination-add";
    }

    @PostMapping("/add")
    public String addDestinationConfirm(@Valid DestinationAddBindingModel destinationAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {

        if (this.destinationService.isContains(this.modelMapper.map(destinationAddBindingModel, DestinationServiceModel.class))) {
            redirectAttributes.addFlashAttribute("destinationExist", true);
            return "redirect:add";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("destinationAddBindingModel", destinationAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.destinationAddBindingModel", bindingResult);

            return "redirect:add";
        }

        destinationService.addDestination(this.modelMapper.map(destinationAddBindingModel, DestinationServiceModel.class));

        return "redirect:/";
    }


}
