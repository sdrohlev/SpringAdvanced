package com.example.dorne.web;

import com.example.dorne.model.binding.ListingAddBindingModel;
import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.model.service.ListingServiceModel;
import com.example.dorne.service.DestinationService;
import com.example.dorne.service.ListingService;
import com.example.dorne.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/listings")
public class ListingController {

    private final ListingService listingService;
    private final ModelMapper modelMapper;
    private final DestinationService destinationService;
    private final UserService userService;

    public ListingController(ListingService listingService,
                             ModelMapper modelMapper,
                             DestinationService destinationService,
                             UserService userService) {
        this.listingService = listingService;
        this.modelMapper = modelMapper;
        this.destinationService = destinationService;
        this.userService = userService;
    }

    @GetMapping("/hotels")
    public String hotels(Model model) {

        model.addAttribute("hotels", this.listingService.findByCategoryName(CategoryNameEnum.HOTEL));
        return "hotels";
    }

    @GetMapping("/restaurants")
    public String restaurants(Model model) {

        model.addAttribute("restaurants", this.listingService.findByCategoryName(CategoryNameEnum.RESTAURANT));
        return "restaurants";
    }

    @GetMapping("/shopping")
    public String shopping(Model model) {

        model.addAttribute("shopping", this.listingService.findByCategoryName(CategoryNameEnum.SHOPPING));
        return "shopping";
    }

    @GetMapping("/fitness")
    public String beautyandspa(Model model) {

        model.addAttribute("fitness", this.listingService.findByCategoryName(CategoryNameEnum.FITNESS));
        return "fitness";
    }

    @GetMapping("/cinemas")
    public String cinemas(Model model) {

        model.addAttribute("cinemas", this.listingService.findByCategoryName(CategoryNameEnum.CINEMA));
        return "cinemas";
    }

    @GetMapping("/add")
    public String addListing(Model model) {
        if (!model.containsAttribute("listingAddBindingModel")) {
            model.addAttribute("listingAddBindingModel", new ListingAddBindingModel());
            model.addAttribute("destinations", this.destinationService.findAll());
        }

        return "listing-add";
    }

    @PostMapping("/add")
    public String addListingConfirm(@Valid ListingAddBindingModel listingAddBindingModel,
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

    @GetMapping("/{id}")
    public String singleListing(Model model, @PathVariable("id") String id) {

        model.addAttribute("listing", this.listingService.findById(id));
        return "single-listing";
    }

    @GetMapping("/mylistings")
    public String listingsByUser(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUserEmail = authentication.getName();
        UserEntity authUser = this.userService.findByEmailAuth(authUserEmail);
        model.addAttribute("authUserListings", this.listingService.findByUserId(authUser.getId()));
        return "auth-user-listings";
    }

}



