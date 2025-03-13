package com.example.NoteDrop.service.IMPL;

import com.example.NoteDrop.dto.LoginDTO;
import com.example.NoteDrop.dto.UserDTO;
import com.example.NoteDrop.entity.User;
import com.example.NoteDrop.repo.UserRepo;
import com.example.NoteDrop.response.LoginResponse;
import com.example.NoteDrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    UserDTO userDTO;

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        User user = userRepo.findByUsername(loginDTO.getUsername());

        if (user == null) {
            return new LoginResponse("Username does not exist", false);
        }
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return new LoginResponse("Login Success", true);
        } else {
            return new LoginResponse("Password Incorrect", false);
        }
    }

}
