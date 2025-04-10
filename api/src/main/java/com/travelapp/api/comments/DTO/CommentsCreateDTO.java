package com.travelapp.api.comments.DTO;

public class CommentsCreateDTO {
    // The ID of the user creating this comment.
    private Long userId;
    
    // The ID of the activity that this comment is related to (optional).
    private Long activityId;
    
    // The ID of the itinerary that this comment is related to (optional).
    private Long itineraryId;
    
    // The text of the comment.
    private String comment;

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
    
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}

