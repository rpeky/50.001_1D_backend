package com.travelapp.api.likes.service;

import com.travelapp.api.likes.DTO.LikesCreateDTO;
import com.travelapp.api.likes.DTO.LikesReadDTO;
import java.util.List;

public interface LikesService {
    LikesReadDTO createLike(LikesCreateDTO createDTO);
    LikesReadDTO getLike(Long likeId);
    List<LikesReadDTO> getAllLikes();
    List<LikesReadDTO> getLikesByUser(Long userId);
    List<LikesReadDTO> getLikesByActivity(Long activityId);
    long countLikesByActivity(Long activityId);
    void deleteLike(Long likeId);
    
    List<LikesReadDTO> getLikesByUserUuid(String userUid);
}

