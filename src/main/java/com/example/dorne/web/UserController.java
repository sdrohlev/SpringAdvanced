package com.example.dorne.web;


import com.example.dorne.model.binding.UserRegisterBindingModel;
import com.example.dorne.model.binding.UserRoleBindingModel;
import com.example.dorne.model.service.UserServiceModel;
import com.example.dorne.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }


    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("email") String email,
            Model model) {

        model.addAttribute("email", email);
        model.addAttribute("bad_credentials", "true");

        return "auth-login";
    }


    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        userService.register(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/change-roles")
    public String changeRoles(Model model) {

        if (!model.containsAttribute("userRoleBindingModel")) {
            model.addAttribute("userRoleBindingModel", new UserRoleBindingModel());
            model.addAttribute("users", this.userService.findAllUsers());
        }

        return "change-roles";
    }

    @PostMapping("/change-roles")
    public String changeRolesConfirm(@Valid UserRoleBindingModel userRoleBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRoleBindingModel", userRoleBindingModel);
            redirectAttributes.addFlashAttribute("users", this.userService.findAllUsers());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRoleBindingModel", bindingResult);

            return "redirect:change-roles";
        }

        if (this.userService.isUserRoleEqual(userRoleBindingModel.getEmail(), userRoleBindingModel.getNewRole())) {
            redirectAttributes.addFlashAttribute("equalRoles", true);
            return "redirect:change-roles";
        }

        this.userService.changeUserRole(userRoleBindingModel.getEmail(),userRoleBindingModel.getNewRole());
        return "redirect:/";
    }

}
