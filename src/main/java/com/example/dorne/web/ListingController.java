package com.example.dorne.web;

import com.example.dorne.model.binding.ListingAddBindingModel;
import com.example.dorne.model.service.ListingServiceModel;
import com.example.dorne.service.ListingService;
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
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;
    private final ModelMapper modelMapper;

    public ListingController(ListingService listingService, ModelMapper modelMapper) {
        this.listingService = listingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/hotels")
    public String hotels() {
        return "hotels";
    }

    @GetMapping("/restaurants")
    public String restaurants() {
        return "restaurants";
    }

    @GetMapping("/shopping")
    public String shopping() {
        return "shopping";
    }

    @GetMapping("/beautyandspa")
    public String beautyandspa() {
        return "beautyandspa";
    }

    @GetMapping("/cinemas")
    public String cinemas() {
        return "cinemas";
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("listingAddBindingModel")) {
            model.addAttribute("listingAddBindingModel", new ListingAddBindingModel());
        }

        return "listing-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ListingAddBindingModel listingAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (this.listingService.isContains(this.modelMapper.map(listingAddBindingModel, ListingServiceModel.class))) {
            redirectAttributes.addFlashAttribute("listingExist", true);
            return "redirect:add";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("listingAddBindingModel", listingAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.listingAddBindingModel", bindingResult);

            return "redirect:add";
        }

        listingService.addListing(this.modelMapper.map(listingAddBindingModel, ListingServiceModel.class));

        return "redirect:/";
    }
}



