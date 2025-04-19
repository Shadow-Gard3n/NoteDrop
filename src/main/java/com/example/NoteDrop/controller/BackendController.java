package com.example.NoteDrop.controller;

import com.example.NoteDrop.dto.NotesSaveDTO;
import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.service.NotesService;
import com.example.NoteDrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class BackendController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotesService notesService;

    @PostMapping("/signup")
    public String saveUser(@ModelAttribute UserSaveDTO userSaveDTO, RedirectAttributes redirectAttributes) {
        String username = userSaveDTO.getUsername();

        if (username == null || username.trim().isEmpty() || !username.matches("^[a-zA-Z0-9_@]+$")) {
            redirectAttributes.addFlashAttribute("error", "Invalid username! Only letters, numbers, underscore and @ are allowed.");
            return "redirect:/signup";
        }

        if (userSaveDTO.getPassword().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Password cannot be empty!");
            return "redirect:/signup";
        }

        if (userService.userExists(userSaveDTO.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Username already exists. Try another.");
            return "redirect:/signup";
        }
        userService.addUser(userSaveDTO);
        return "redirect:/login";
    }

    @PostMapping("/notes/save")
    public String saveNote(@RequestParam("username") String username,
                                           @RequestParam("subject") String subject,
                                           @RequestParam("topic") String topic,
                                           @RequestParam("about") String about,
                                           @RequestParam("file") MultipartFile file
    ) {

        if (file.isEmpty()) {
            return "redirect:/" + username + "/home?error=emptyFile";
        }
        if (username.length() > 100) {
            return "redirect:/" + username + "/home?error=usernameTooLong";
        }
        if (subject.length() > 60) {
            return "redirect:/" + username + "/home?error=subjectTooLong";
        }
        if (topic.length() > 60) {
            return "redirect:/" + username + "/home?error=topicTooLong";
        }
        if (about.length() > 1000) {
            return "redirect:/" + username + "/home?error=aboutTooLong";
        }
        Path uploadPath = Paths.get("src/main/resources/static/uploads/");
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        try {
            byte[] fileBytes = file.getBytes();
            Files.write(filePath, fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/" + username + "/home?error=uploadFailed";        }

        NotesSaveDTO notesSaveDTO = new NotesSaveDTO(username, subject, topic, about, "/uploads/"+fileName);
        notesService.addNotes(notesSaveDTO);

        return "redirect:/" + username + "/home?uploadSuccess";
    }


    @PostMapping("/follow")
    @ResponseBody
    public ResponseEntity<String> followUser(@RequestParam String usernameToFollow, @RequestHeader("X-CSRF-TOKEN") String csrfToken, Authentication authentication) {
        String currentUsername = authentication.getName();
        try {
            userService.followUser(currentUsername, usernameToFollow);
            return ResponseEntity.ok("Followed successfully");
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
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
