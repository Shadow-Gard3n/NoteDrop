package com.example.NoteDrop.service;

import com.example.NoteDrop.dto.LoginDTO;
import com.example.NoteDrop.dto.UserDTO;
import com.example.NoteDrop.response.LoginResponse;

public interface UserService {

    String addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
}
