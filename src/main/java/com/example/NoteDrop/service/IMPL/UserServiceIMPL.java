package com.example.NoteDrop.service.IMPL;

import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.entity.User;
import com.example.NoteDrop.repo.UserRepo;
import com.example.NoteDrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;


@Service
public class UserServiceIMPL implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void addUser(UserSaveDTO userSaveDTO) {

        User user = new User(
                userSaveDTO.getUsername(),
                this.passwordEncoder.encode(userSaveDTO.getPassword())
        );
        userRepo.save(user);
    }

//    @Override
//    public boolean loginUser(String username, String password) {
//        User user = userRepo.findByUsername(username).orElse(null);
//
//        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}