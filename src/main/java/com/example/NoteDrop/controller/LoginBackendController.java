package com.example.NoteDrop.controller;

import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin
@RequestMapping("/api")
public class LoginBackendController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute UserSaveDTO userSaveDTO, Model model) {
        if (userService.userExists(userSaveDTO.getUsername())) {
            model.addAttribute("error", "Username already exists. Try another.");
            return "signup";
        }
        userService.addUser(userSaveDTO);
        return "redirect:/login";
    }


//    @PostMapping("/login")
//    public String loginUser(@RequestParam String username,
//                            @RequestParam String password,
//                            Model model) {
//        boolean success = userService.loginUser(username, password);
//
//        if (success) {
//            UserDetails userDetails = userService.loadUserByUsername(username);
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//
//            return "redirect:/home";
//        }
//
//        model.addAttribute("error", "Invalid username or password");
//        return "login";
//    }

}
