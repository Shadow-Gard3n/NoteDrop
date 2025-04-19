package com.example.NoteDrop.service.IMPL;

import com.example.NoteDrop.dto.UserSaveDTO;
import com.example.NoteDrop.entity.Follow;
import com.example.NoteDrop.entity.User;
import com.example.NoteDrop.repo.FollowRepo;
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
import java.util.List;


@Service
public class UserServiceIMPL implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean userExists(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

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

    @Autowired
    private FollowRepo followRepo;

    public void followUser(String followerUsername, String followedUsername) {
        if (followerUsername.equals(followedUsername)) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }

        User follower = userRepo.findByUsername(followerUsername)
                .orElseThrow(() -> new IllegalArgumentException("Follower user not found."));

        User followed = userRepo.findByUsername(followedUsername)
                .orElseThrow(() -> new IllegalArgumentException("User to follow not found."));

        boolean alreadyFollowing = followRepo.existsByFollowerAndFollowed(follower, followed);
        if (alreadyFollowing) {
            throw new IllegalStateException("You are already following this user.");
        }

        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowed(followed);

        followRepo.save(follow);
    }

    @Override
    public List<String> getFollowedUsernames(String username) {
        User follower = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return followRepo.findFollowedUsernamesByFollower(follower);
    }

}