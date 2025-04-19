package com.example.NoteDrop.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

///  maybe need to add notecount followers following
/// somehow need to store the data of saved notes (maybe need to try using notesid)
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id",length = 20)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(name = "username",length = 100)
    private String username;

    @Column(name = "password",length = 200)
    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "user_follow",
//            joinColumns = @JoinColumn(name = "follower_id"), // current user
//            inverseJoinColumns = @JoinColumn(name = "followed_id") // user they follow
//    )
//    private Set<User> following;
//
//    @ManyToMany(mappedBy = "following")
//    private Set<User> followers;


    public User() {
    }
    public User(int userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public User(int userid, String username, String password, Set<User> followers, Set<User> following) {
//        this.userid = userid;
//        this.username = username;
//        this.password = password;
//        this.followers = followers;
//        this.following = following;
//    }

//    public User(String username, String password, Set<User> followers, Set<User> following) {
//        this.username = username;
//        this.password = password;
//        this.followers = followers;
//        this.following = following;
//    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<User> getFollowers() {
//        return followers;
//    }

//    public void setFollowers(Set<User> followers) {
//        this.followers = followers;
//    }
//
//    public Set<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(Set<User> following) {
//        this.following = following;
//    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", followers='" + followers + '\'' +
//                ", following='" + following + '\'' +
                '}';
    }
}
