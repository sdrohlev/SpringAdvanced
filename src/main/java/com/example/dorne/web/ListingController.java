package com.example.dorne.web;

import com.example.dorne.model.binding.ListingAddBindingModel;
import com.example.dorne.model.binding.ReviewAddBindingModel;
import com.example.dorne.model.binding.SearchDestinationBindingModel;
import com.example.dorne.model.entity.Listing;
import com.example.dorne.model.entity.UserEntity;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.model.entity.enums.UserRoleEnum;
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

import java.util.List;

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

    @GetMapping("/destination/{id}")
    public String ListingsByDestination(Model model, @PathVariable("id") String id) {

        model.addAttribute("destination", this.destinationService.findById(id));
        model.addAttribute("listingsByDest", this.listingService.findByDestination(id));
        return "listings-by-destination";
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
        if (!model.containsAttribute("reviewAddBindingModel")) {
            model.addAttribute("reviewAddBindingModel", new ReviewAddBindingModel());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUserEmail = authentication.getName();
        UserEntity authUser = this.userService.findByEmailAuth(authUserEmail);
        model.addAttribute("authUser", authUser);
        model.addAttribute("listing", this.listingService.findById(id));
        return "single-listing";
    }

    @PostMapping("/{id}")
    public String singleListingAddReview(@PathVariable("id") String id,
                                         @Valid ReviewAddBindingModel reviewAddBindingModel,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("reviewAddBindingModel", reviewAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reviewAddBindingModel", bindingResult);

            return "redirect:{id}";
        }

        this.listingService.addReviewToListing(id, reviewAddBindingModel.getReview());
        return "redirect:{id}";
    }

    @GetMapping("/mylistings")
    public String listingsByUser(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUserEmail = authentication.getName();
        UserEntity authUser = this.userService.findByEmailAuth(authUserEmail);
        model.addAttribute("authUserListings", this.listingService.findByUserId(authUser.getId()));

        return "auth-user-listings";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUserEmail = authentication.getName();
        UserEntity authUser = this.userService.findByEmailAuth(authUserEmail);
        String listingUserEmail = this.listingService.findById(id).getUser().getEmail();

        if (authUserEmail.equals(listingUserEmail) ||  authUser.getRole().equals(UserRoleEnum.ADMIN)) {
            this.listingService.removeListing(id);
        }

        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(Model model) {

        return "listings-by-search";
    }
    @PostMapping("/search")
    public String searchConfirm(@Valid SearchDestinationBindingModel searchDestinationBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchDestinationBindingModel", searchDestinationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.searchDestinationBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("destinations", this.destinationService.findAll());

            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("listingsBySearch", this.listingService.findAllByDestinationAndCategory(searchDestinationBindingModel.getDestination(), searchDestinationBindingModel.getCategory()));
        redirectAttributes.addFlashAttribute("searchedDest", searchDestinationBindingModel.getDestination());
        redirectAttributes.addFlashAttribute("searchedCat", searchDestinationBindingModel.getCategory());

        return "redirect:/listings/search";
    }

}



