package com.example.dorne.web;


import com.example.dorne.repository.UserRepository;
import com.example.dorne.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String returnHome(Model model) {
        return "home";
    }

}
