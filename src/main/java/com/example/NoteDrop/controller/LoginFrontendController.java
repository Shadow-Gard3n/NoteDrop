package com.example.NoteDrop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin
@RequestMapping("/")
public class LoginFrontendController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

}
