package com.example.NoteDrop.service.IMPL;

import com.example.NoteDrop.dto.UserDTO;
import com.example.NoteDrop.entity.User;
import com.example.NoteDrop.repo.UserRepo;
import com.example.NoteDrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {

        User user = new User(
                userDTO.getUserid(),
                userDTO.getUsername(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepo.save(user);
        return user.getUsername();
    }
}
