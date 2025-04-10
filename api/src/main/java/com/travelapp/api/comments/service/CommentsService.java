package com.travelapp.api.comments.service;

import com.travelapp.api.comments.DTO.CommentsCreateDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.comments.DTO.CommentsUpdateDTO;
import java.util.List;

public interface CommentsService {
    CommentsReadDTO createComment(CommentsCreateDTO createDTO);
    CommentsReadDTO updateComment(CommentsUpdateDTO updateDTO);
    CommentsReadDTO getComment(Long commentId);
    List<CommentsReadDTO> getAllComments();
    List<CommentsReadDTO> getCommentsByUser(Long userId);
    List<CommentsReadDTO> getCommentsByUserUuid(String userUid);
    List<CommentsReadDTO> getCommentsByActivity(Long activityId);
    List<CommentsReadDTO> getCommentsByItinerary(Long itineraryId);
    long countCommentsByActivity(Long activityId);
    void deleteComment(Long commentId);
}

