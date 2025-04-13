package com.travelapp.api.users.userfollows.DTO;

import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;
import com.travelapp.api.users.entity.Users;

public class FollowerListDTO {

    private Long followId;
    private UsersOtherReadDTO follower;

    public FollowerListDTO(Long followId, UsersOtherReadDTO follower) {
        this.followId = followId;
        this.follower = follower;
    }

    public FollowerListDTO() {
    }

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
}
