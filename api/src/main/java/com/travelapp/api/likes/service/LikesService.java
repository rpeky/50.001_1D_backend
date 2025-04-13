package com.travelapp.api.likes.service;

import com.travelapp.api.likes.DTO.LikesCreateDTO;
import com.travelapp.api.likes.DTO.LikesReadDTO;

public interface LikesService {
    LikesReadDTO createLikeActivity(LikesCreateDTO likesCreateDTO);
    LikesReadDTO createLikeItinerary(LikesCreateDTO likesCreateDTO);

    void deleteLikeActivity(String userUid, Long activityId);
    void deleteLikeItinerary(String userUid, Long itineraryId);


}
