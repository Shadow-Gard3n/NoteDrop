package com.example.NoteDrop.service;

import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

public interface UserService {
    void addUser(UserSaveDTO userSaveDTO);
//    boolean loginUser(String username, String password);
    boolean userExists(String username);
    UserDetails loadUserByUsername(String username);
    public void followUser(String followerUsername, String followedUsername);
    List<String> getFollowedUsernames(String username);
}
