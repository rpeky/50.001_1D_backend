package com.travelapp.api.users.extrainfo;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.userfollows.DTO.FollowerListDTO;
import com.travelapp.api.users.userfollows.DTO.FollowingListDTO;
import com.travelapp.api.users.userfollows.entity.UserFollows;
import com.travelapp.api.users.userfollows.repository.UserFollowsRepository;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UsersExtraInfoCalculation {

    public static UserExtraInfo calculateUserExtraInfo(Users retrievedUser, UserFollowsRepository userFollowsRepository,
                                                       ModelMapper strictMapper) {

        Long userId = retrievedUser.getUserId();

        List<UserFollows> followingList = userFollowsRepository.findByFollower_UserId(userId);
        Long followingCount = (long) followingList.size();

        List<UserFollows> followerList = userFollowsRepository.findByFollowing_UserId(userId);
        Long followerCount = (long) followerList.size();

        List<Activities> allActivities =  retrievedUser.getActivities();

        Long likesCount = 0L;
        for (Activities activity : allActivities) {
            Long activityLikeCount = activity.getLikes();
            if (activityLikeCount != null) {
                likesCount += activityLikeCount;
            }
        }

        List<FollowingListDTO> followingListToReturn = new ArrayList<>();
        for (UserFollows follow : followingList) {
            Users followingUser = follow.getFollowing();
            UsersOtherReadDTO followingUserToAdd = strictMapper.map(followingUser, UsersOtherReadDTO.class);
            Long followId = follow.getFollowId();
            FollowingListDTO followingListDTO = new FollowingListDTO(followId, followingUserToAdd);
            followingListToReturn.add(followingListDTO);
        }

        List<FollowerListDTO> followerListToReturn = new ArrayList<>();
        for (UserFollows follow : followerList) {
            Users followerUser = follow.getFollower();
            UsersOtherReadDTO followerUserToAdd = strictMapper.map(followerUser, UsersOtherReadDTO.class);
            Long followId = follow.getFollowId();
            FollowerListDTO followerListDTO = new FollowerListDTO(followId, followerUserToAdd);
            followerListToReturn.add(followerListDTO);
        }

        UserExtraInfo extraInfo = new UserExtraInfo(followingListToReturn, followingCount,
                followerListToReturn, followerCount, likesCount);

        return extraInfo;

    }
}
