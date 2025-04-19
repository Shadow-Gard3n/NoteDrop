package com.example.NoteDrop.controller;

import com.example.NoteDrop.entity.Notes;
import com.example.NoteDrop.repo.FollowRepo;
import com.example.NoteDrop.service.NotesService;
import com.example.NoteDrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/{username}")
public class HomeController {

    @Autowired
    private NotesService notesService;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowRepo followRepo;

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
        List<Notes> userNotes = notesService.notesByUsername(username);

        int followersCount = followRepo.findFollowersUsernamesByUsername(username).size();
        int followingCount = followRepo.findFollowedUsernamesByUsername(username).size();
        model.addAttribute("followersCount", followersCount);
        model.addAttribute("followingCount", followingCount);

        model.addAttribute("notesCount", userNotes.size());
        model.addAttribute("userNotes", userNotes);
        return "profile";
    }

    @GetMapping("/search")
    public String search(@PathVariable String username,
                         @RequestParam (name = "query") String query,
                         Authentication authentication,
                         Model model) {
        if (!authentication.getName().equals(username)) {
            return "redirect:/" + authentication.getName() + "/profile";
        }
        List<Notes> Results = notesService.searchNotes(query);
        List<String> followedUsernames = userService.getFollowedUsernames(authentication.getName());

        model.addAttribute("followedUsernames", followedUsernames);
        model.addAttribute("Results", Results);
        model.addAttribute("query", query);
        model.addAttribute("username", username);
        return "search";
    }
}

