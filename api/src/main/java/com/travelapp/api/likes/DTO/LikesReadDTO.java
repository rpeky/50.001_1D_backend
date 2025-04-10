package com.travelapp.api.likes.DTO;

import java.time.LocalDateTime;

public class LikesReadDTO {
    private Integer likeId;
    private Long userId;
    private Long activityId;
    private Long itineraryId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // Getters and Setters
    public Integer getLikeId() {
        return likeId;
    }
    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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

