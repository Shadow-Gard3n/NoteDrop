package com.example.NoteDrop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "follows")
public class Follow {
    @Id
    private String id;

    // Storing usernames directly is more efficient in NoSQL for this use case
    private String followerUsername; 
    private String followedUsername;

    public Follow() {
    }

    public Follow(String followerUsername, String followedUsername) {
        this.followerUsername = followerUsername;
        this.followedUsername = followedUsername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollowerUsername() {
        return followerUsername;
    }

    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }

    public String getFollowedUsername() {
        return followedUsername;
    }

    public void setFollowedUsername(String followedUsername) {
        this.followedUsername = followedUsername;
    }
}

// package com.example.NoteDrop.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "user_follow")
// public class Follow {
//     @Id
//     @GeneratedValue
//     private Long id;

//     @ManyToOne
//     private User follower;

//     @ManyToOne
//     private User followed;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public User getFollower() {
//         return follower;
//     }

//     public void setFollower(User follower) {
//         this.follower = follower;
//     }

//     public User getFollowed() {
//         return followed;
//     }

//     public void setFollowed(User followed) {
//         this.followed = followed;
//     }

//     public Follow(User follower, User followed) {
//         this.follower = follower;
//         this.followed = followed;
//     }

//     public Follow() {

//     }

//     @Override
//     public String toString() {
//         return "Follow{" +
//                 "id=" + id +
//                 ", follower=" + follower +
//                 ", followed=" + followed +
//                 '}';
//     }
// }
