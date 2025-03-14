package com.example.NoteDrop.controller;

import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.service.UserService;
import com.example.NoteDrop.dto.LoginDTO;
import com.example.NoteDrop.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class LoginBackendController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute UserSaveDTO userSaveDTO) {
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
