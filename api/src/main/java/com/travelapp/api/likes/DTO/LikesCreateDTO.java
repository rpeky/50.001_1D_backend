package com.travelapp.api.likes.DTO;

public class LikesCreateDTO {
    private Long userId;
    private Long activityId;
    // Optional: itineraryId if needed
    private Long itineraryId;

    // Getters and Setters
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
}

