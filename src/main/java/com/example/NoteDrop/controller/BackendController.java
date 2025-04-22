package com.example.NoteDrop.controller;

import com.example.NoteDrop.config.SupabaseConfig;
import com.example.NoteDrop.dto.NotesSaveDTO;
import com.example.NoteDrop.dto.SaveNotesDTO;
import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.service.NotesService;
import com.example.NoteDrop.service.UserService;
import com.google.cloud.storage.BlobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class BackendController {

    @Autowired
    private SupabaseConfig supabaseConfig;

    @Autowired
    private RestTemplate restTemplate;

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
                           @RequestParam("file") MultipartFile file) {

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

        try {

            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            String supabaseUrl = supabaseConfig.getSupabaseUrl();
            String supabaseApiKey = supabaseConfig.getApiKey();

            // prepare the file data for upload to supabase storage
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + supabaseApiKey);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            // Convert the file to byte array
            byte[] fileBytes = file.getBytes();

            // Create the HTTP request body
            HttpEntity<byte[]> entity = new HttpEntity<>(fileBytes, headers);

            // Send the file to Supabase Storage
            String uploadUrl = supabaseUrl + "/object/notes-bucket/" + uniqueFileName;
            ResponseEntity<String> response = restTemplate.exchange(uploadUrl, HttpMethod.POST, entity, String.class);

            // Handle the response and retrieve the file URL
            if (response.getStatusCode() == HttpStatus.OK) {
                String fileUrl = "https://hqgxdjghhkmvdwanoxgl.supabase.co/storage/v1/object/public/notes-bucket/" + uniqueFileName;

                // Create DTO for saving note
                NotesSaveDTO notesSaveDTO = new NotesSaveDTO(username, subject, topic, about, fileUrl);
                notesService.addNotes(notesSaveDTO);
            } else {
                return "redirect:/" + username + "/home?error=uploadFailed";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/" + username + "/home?error=uploadFailed";
        }

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

    @PostMapping("/savePDF")
    @ResponseBody
    public ResponseEntity<String> savePDF(@RequestParam String username, @RequestParam int pdfId) {
        boolean saved = notesService.saveNoteForUser(username, pdfId);
        try {
            if (saved) {
                return ResponseEntity.ok("Saved successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Already saved!");
            }
        }
        catch (Exception e) {
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
