package com.example.NoteDrop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_follow")
public class Follow {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User followed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }

    public Follow(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
    }
    public Follow() {

    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", follower=" + follower +
                ", followed=" + followed +
                '}';
    }
}
