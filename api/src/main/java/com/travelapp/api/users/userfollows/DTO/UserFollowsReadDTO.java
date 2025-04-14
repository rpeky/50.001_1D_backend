package com.travelapp.api.users.userfollows.DTO;

import com.travelapp.api.users.DTO.UsersReadElseDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.time.LocalDateTime;

public class UserFollowsReadDTO {

    private Long followId;
    private UsersOtherReadDTO follower;
    private UsersOtherReadDTO following;
    private LocalDateTime createdAt;

    public Long getFollowId() {
        return followId;
    }
    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public UsersOtherReadDTO getFollower() {
        return follower;
    }
    public void setFollower(UsersOtherReadDTO follower) {
        this.follower = follower;
    }

    public UsersOtherReadDTO getFollowing() {
        return following;
    }
    public void setFollowing(UsersOtherReadDTO following) {
        this.following = following;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
