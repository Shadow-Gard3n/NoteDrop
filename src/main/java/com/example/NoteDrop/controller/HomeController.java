//package com.example.NoteDrop.controller;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@CrossOrigin
//@RequestMapping("/home")
//public class HomeController {
//    @GetMapping("")
//    public String home() {
//        return "home";
//    }
//
//}

package com.example.NoteDrop.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/{username}")
public class HomeController {

    @GetMapping("/home")
    public String home(@PathVariable String username,
                       Authentication authentication,
                       Model model) {
        if (!authentication.getName().equals(username)) {
            return "redirect:/" + authentication.getName() + "/home";
        }
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/profile")
    public String profile(@PathVariable String username,
                          Authentication authentication,
                          Model model) {
        if (!authentication.getName().equals(username)) {
            return "redirect:/" + authentication.getName() + "/profile";
        }
        model.addAttribute("username", username);
        return "profile";
    }
}

