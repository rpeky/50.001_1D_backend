package com.travelapp.api.activities.service;

import com.travelapp.api.activities.DTO.ActivitiesCreateDTO;
import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.activities.DTO.ActivitiesUpdateDTO;

import java.util.List;

public interface ActivitiesService {
    ActivitiesReadDTO createActivity(ActivitiesCreateDTO activitiesCreateDTO);
    ActivitiesReadDTO updateActivity(ActivitiesUpdateDTO activitiesUpdateDTO);
    ActivitiesReadDTO getActivity(Long activityId);
    List<ActivitiesReadDTO> getAllUserActivities(String userUid);
    ActivitiesReadDTO getUserActivity(String userUid, Long activityId);
    void deleteActivity(Long activityId);
    void deleteUserActivity(String userUid, Long activityId);


}
