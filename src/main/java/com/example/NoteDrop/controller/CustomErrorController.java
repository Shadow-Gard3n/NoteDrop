package com.example.NoteDrop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");

        if (statusCode != null) {
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error", "Page Not Found");
                return "404";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("error", "You do not have permission to access this page.");
                return "error";
            }
        }

        model.addAttribute("error", "Something went wrong!");
        return "error";
    }
}
