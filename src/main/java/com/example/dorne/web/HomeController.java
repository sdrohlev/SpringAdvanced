package com.example.dorne.web;


import com.example.dorne.model.binding.ListingAddBindingModel;
import com.example.dorne.model.binding.SearchDestinationBindingModel;
import com.example.dorne.model.entity.enums.CategoryNameEnum;
import com.example.dorne.repository.UserRepository;
import com.example.dorne.service.DestinationService;
import com.example.dorne.service.UserService;
import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private final DestinationService destinationService;

    public HomeController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/")
    public String returnHome(Model model) {
        if (!model.containsAttribute("searchDestinationBindingModel")) {
            model.addAttribute("searchDestinationBindingModel", new SearchDestinationBindingModel());
            model.addAttribute("destinations", this.destinationService.findAll());
        }

        return "home";
    }

}
