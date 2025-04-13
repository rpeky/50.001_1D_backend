package com.travelapp.api.users.userfollows.DTO;

import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

public class FollowingListDTO {

    private Long followId;
    private UsersOtherReadDTO following;

    public FollowingListDTO() {
    }

    public FollowingListDTO(Long followId, UsersOtherReadDTO following) {
        this.followId = followId;
        this.following = following;
    }

    public Long getFollowId() {
        return followId;
    }
    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public UsersOtherReadDTO getFollowing() {
        return following;
    }
    public void setFollowing(UsersOtherReadDTO following) {
        this.following = following;
    }
}
