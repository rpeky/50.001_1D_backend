package com.travelapp.api.users.userfollows.DTO;

import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.time.LocalDateTime;

public class UserFollowsCreateDTO {

    private UsersOtherCreateDTO follower;
    private UsersOtherCreateDTO following;

    public UsersOtherCreateDTO getFollower() {
        return follower;
    }
    public void setFollower(UsersOtherCreateDTO follower) {
        this.follower = follower;
    }

    public UsersOtherCreateDTO getFollowing() {
        return following;
    }
    public void setFollowing(UsersOtherCreateDTO following) {
        this.following = following;
    }


}
