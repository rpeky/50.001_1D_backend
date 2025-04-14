package com.travelapp.api.users.extrainfo;

import com.travelapp.api.users.userfollows.DTO.FollowerListDTO;
import com.travelapp.api.users.userfollows.DTO.FollowingListDTO;

import java.util.List;

public class UserExtraInfo {
    private List<FollowingListDTO> followingList;
    private Long followingCount;
    private List<FollowerListDTO> followerList;
    private Long followerCount;
    private Long likesCount;

    public UserExtraInfo() {
    }

    public UserExtraInfo(List<FollowingListDTO> followingList, Long followingCount,
                         List<FollowerListDTO> followerList, Long followerCount, Long likesCount) {
        this.followingList = followingList;
        this.followingCount = followingCount;
        this.followerList = followerList;
        this.followerCount = followerCount;
        this.likesCount = likesCount;
    }

    public List<FollowingListDTO> getFollowingList() {
        return followingList;
    }
    public void setFollowingList(List<FollowingListDTO> followingList) {
        this.followingList = followingList;
    }

    public Long getFollowingCount() {
        return followingCount;
    }
    public void setFollowingCount(Long followingCount) {
        this.followingCount = followingCount;
    }

    public List<FollowerListDTO> getFollowerList() {
        return followerList;
    }
    public void setFollowerList(List<FollowerListDTO> followerList) {
        this.followerList = followerList;
    }

    public Long getFollowerCount() {
        return followerCount;
    }
    public void setFollowerCount(Long followerCount) {
        this.followerCount = followerCount;
    }

    public Long getLikesCount() {
        return likesCount;
    }
    public void setLikesCount(Long likesCount) {
        this.likesCount = likesCount;
    }
}
