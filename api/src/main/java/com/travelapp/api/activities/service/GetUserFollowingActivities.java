package com.travelapp.api.activities.service;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.activities.repository.ActivitiesRepository;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.extrainfo.UserExtraInfo;
import com.travelapp.api.users.extrainfo.UsersExtraInfoCalculation;
import com.travelapp.api.users.userfollows.DTO.FollowerListDTO;
import com.travelapp.api.users.userfollows.DTO.FollowingListDTO;
import com.travelapp.api.users.userfollows.repository.UserFollowsRepository;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class GetUserFollowingActivities {

    public static List<Activities> followingActivities(UserFollowsRepository userFollowsRepository,
                                                       ActivitiesRepository activitiesRepository,
                                                       Users user, ModelMapper strictMapper) {

        // get the user's following list
        UserExtraInfo extraInfo = UsersExtraInfoCalculation
                .calculateUserExtraInfo(user, userFollowsRepository, strictMapper);

        List<FollowingListDTO> followingList = extraInfo.getFollowingList();

        // collect activities from each followed user
        List<Activities> allFollowingActivities = new ArrayList<>();

        for (FollowingListDTO followedUser : followingList) {
            String followedUid = followedUser.getFollowing().getUserUid();
            List<Activities> followedActivities = activitiesRepository.findByCreatedBy_UserUid(followedUid);
            allFollowingActivities.addAll(followedActivities);
        }

        // sort the combined list by createdAt descending (newest first)
        allFollowingActivities.sort((a1, a2) -> a2.getCreatedAt().compareTo(a1.getCreatedAt()));

        return allFollowingActivities;
    }

}
