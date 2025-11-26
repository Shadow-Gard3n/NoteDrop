package com.example.NoteDrop.controller;

import com.example.NoteDrop.entity.Follow;
import com.example.NoteDrop.entity.Notes;
import com.example.NoteDrop.entity.SavedNotes;
import com.example.NoteDrop.repo.FollowRepo;
import com.example.NoteDrop.repo.NotesRepo;
import com.example.NoteDrop.repo.SavedNotesRepo;
import com.example.NoteDrop.service.NotesService;
import com.example.NoteDrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping("/{username}")
public class HomeController {

    @Autowired
    private NotesService notesService;

    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowRepo followRepo;

    @Autowired
    private SavedNotesRepo savedNotesRepo;

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

        List<SavedNotes> savedNotes = savedNotesRepo.findByUsername(username);
        // Changed to String List
        List<String> notesIds = savedNotes.stream()
                .map(SavedNotes::getNoteId)
                .toList();
        
        List<Notes> savedNotesEntities = notesRepo.findByNotesidIn(notesIds);

        // Update follow counts logic using username queries
        int followersCount = followRepo.findByFollowedUsername(username).size();
        int followingCount = followRepo.findByFollowerUsername(username).size();
        
        model.addAttribute("followersCount", followersCount);
        model.addAttribute("followingCount", followingCount);
        model.addAttribute("savedNotes", savedNotesEntities);
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
        
        // Changed to String list
        List<String> savedNoteIds = savedNotesRepo.findByUsername(authentication.getName())
                .stream()
                .map(SavedNotes::getNoteId)
                .toList();
        
        model.addAttribute("savedNoteIds", savedNoteIds);
        model.addAttribute("followedUsernames", followedUsernames);
        model.addAttribute("Results", Results);
        model.addAttribute("query", query);
        model.addAttribute("username", username);
        return "search";
    }

    @GetMapping("/connection")
    public String connection(@PathVariable String username,
                             @RequestParam(name = "search") String searchtype,
                             Authentication authentication,
                             Model model) {
        if (!authentication.getName().equals(username)) {
            return "redirect:/" + authentication.getName() + "/home";
        }
        
        List<String> followers = followRepo.findByFollowedUsername(username)
                .stream().map(Follow::getFollowerUsername).collect(Collectors.toList());
        
        List<String> following = followRepo.findByFollowerUsername(username)
                .stream().map(Follow::getFollowedUsername).collect(Collectors.toList());
                
        model.addAttribute("followers", followers);
        model.addAttribute("following", following);
        model.addAttribute("username", username);
        model.addAttribute("searchtype", searchtype);
        return "connection";
    }
}


// package com.example.NoteDrop.controller;

// import com.example.NoteDrop.entity.Notes;
// import com.example.NoteDrop.entity.SavedNotes;
// import com.example.NoteDrop.repo.FollowRepo;
// import com.example.NoteDrop.repo.NotesRepo;
// import com.example.NoteDrop.repo.SavedNotesRepo;
// import com.example.NoteDrop.service.NotesService;
// import com.example.NoteDrop.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.stream.Collectors;

// @Controller
// @CrossOrigin
// @RequestMapping("/{username}")
// public class HomeController {

//     @Autowired
//     private NotesService notesService;

//     @Autowired
//     private NotesRepo notesRepo;

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private FollowRepo followRepo;

//     @Autowired
//     private SavedNotesRepo savedNotesRepo;

//     @GetMapping("/home")
//     public String home(@PathVariable String username,
//                        Authentication authentication,
//                        Model model) {
//         if (!authentication.getName().equals(username)) {
//             return "redirect:/" + authentication.getName() + "/home";
//         }
//         model.addAttribute("username", username);
//         return "home";
//     }

//     @GetMapping("/profile")
//     public String profile(@PathVariable String username,
//                           Authentication authentication,
//                           Model model) {
//         if (!authentication.getName().equals(username)) {
//             return "redirect:/" + authentication.getName() + "/profile";
//         }
//         List<Notes> userNotes = notesService.notesByUsername(username);

//         List<SavedNotes> savedNotes = savedNotesRepo.findByUsername(username);
//         List<Integer> notesIds = savedNotes.stream()
//                 .map(SavedNotes::getPdfId)
//                 .toList();
//         List<Notes> savedNotesEntities = notesRepo.findByNotesidIn(notesIds);

//         int followersCount = followRepo.findFollowersUsernamesByUsername(username).size();
//         int followingCount = followRepo.findFollowedUsernamesByUsername(username).size();
//         model.addAttribute("followersCount", followersCount);
//         model.addAttribute("followingCount", followingCount);
//         model.addAttribute("savedNotes",savedNotesEntities);  /// just for testing need to correct
//         model.addAttribute("notesCount", userNotes.size());
//         model.addAttribute("userNotes", userNotes);
//         return "profile";
//     }

//     @GetMapping("/search")
//     public String search(@PathVariable String username,
//                          @RequestParam (name = "query") String query,
//                          Authentication authentication,
//                          Model model) {
//         if (!authentication.getName().equals(username)) {
//             return "redirect:/" + authentication.getName() + "/profile";
//         }
//         List<Notes> Results = notesService.searchNotes(query);
//         List<String> followedUsernames = userService.getFollowedUsernames(authentication.getName());
// //        List<SavedNotes> savedNotes = savedNotesRepo.findByUsername(authentication.getName());
//         List<Integer> savedNoteIds = savedNotesRepo.findByUsername(authentication.getName())
//                 .stream()
//                 .map(SavedNotes::getPdfId)
//                 .toList();
//         model.addAttribute("savedNoteIds", savedNoteIds);
// //        model.addAttribute("savedNotes", savedNotes);
//         model.addAttribute("followedUsernames", followedUsernames);
//         model.addAttribute("Results", Results);
//         model.addAttribute("query", query);
//         model.addAttribute("username", username);
//         return "search";
//     }

//     @GetMapping("/connection")
//     public String connection(@PathVariable String username,
//                              @RequestParam(name = "search") String searchtype,
//                              Authentication authentication,
//                              Model model) {
//         if (!authentication.getName().equals(username)) {
//             return "redirect:/" + authentication.getName() + "/home";
//         }
//         List<String> followers= followRepo.findFollowersUsernamesByUsername(username);
//         List<String> following = followRepo.findFollowedUsernamesByUsername(username);
//         model.addAttribute("followers", followers);
//         model.addAttribute("following", following);
//         model.addAttribute("username", username);
//         model.addAttribute("searchtype", searchtype);
//         return "connection";
//     }
// }

