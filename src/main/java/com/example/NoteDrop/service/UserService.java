package com.example.NoteDrop.service;

import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void addUser(UserSaveDTO userSaveDTO);
//    boolean loginUser(String username, String password);
    boolean userExists(String username);
    UserDetails loadUserByUsername(String username);
}
