package com.travelapp.api.users.userfollows.entity;

import com.travelapp.api.users.entity.Users;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_follows")
public class UserFollows {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id", nullable = false)
    private Users follower;

    @ManyToOne
    @JoinColumn(name = "following_id", referencedColumnName = "user_id", nullable = false)
    private Users following;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UserFollows() {
    }

    public UserFollows(Users follower, Users following) {
        this.follower = follower;
        this.following = following;
    }

    public Long getFollowId() {
        return followId;
    }
    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public Users getFollower() {
        return follower;
    }
    public void setFollower(Users follower) {
        this.follower = follower;
    }

    public Users getFollowing() {
        return following;
    }
    public void setFollowing(Users following) {
        this.following = following;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
