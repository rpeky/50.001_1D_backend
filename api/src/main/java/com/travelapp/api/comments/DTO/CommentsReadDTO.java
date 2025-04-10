package com.travelapp.api.comments.DTO;

import com.travelapp.api.users.DTO.UsersReadSelfDTO;
import java.time.LocalDateTime;

public class CommentsReadDTO {
    private Long commentId;
    private String comment;
    
    // Nested DTO from your users module for the creator’s basic info
    private UsersReadSelfDTO createdBy;
    
    // If this comment is attached to an activity or itinerary, include their IDs.
    private Long activityId;
    private Long itineraryId;
    
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // Getters and Setters

    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public UsersReadSelfDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersReadSelfDTO createdBy) {
        this.createdBy = createdBy;
    }
    
    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
    
    public Long getItineraryId() {
        return itineraryId;
    }
    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

