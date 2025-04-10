package com.travelapp.api.comments.DTO;

import org.openapitools.jackson.nullable.JsonNullable;

public class CommentsUpdateDTO {
    private JsonNullable<Long> commentId = JsonNullable.undefined();
    private JsonNullable<String> comment = JsonNullable.undefined();
    private JsonNullable<Long> activityId = JsonNullable.undefined();
    private JsonNullable<Long> itineraryId = JsonNullable.undefined();
    // Optionally, if you allow updating the creator:
    private JsonNullable<Long> userId = JsonNullable.undefined();

    // Getters and Setters

    public JsonNullable<Long> getCommentId() {
        return commentId;
    }
    public void setCommentId(JsonNullable<Long> commentId) {
        this.commentId = commentId;
    }
    
    public JsonNullable<String> getComment() {
        return comment;
    }
    public void setComment(JsonNullable<String> comment) {
        this.comment = comment;
    }
    
    public JsonNullable<Long> getActivityId() {
        return activityId;
    }
    public void setActivityId(JsonNullable<Long> activityId) {
        this.activityId = activityId;
    }
    
    public JsonNullable<Long> getItineraryId() {
        return itineraryId;
    }
    public void setItineraryId(JsonNullable<Long> itineraryId) {
        this.itineraryId = itineraryId;
    }
    
    public JsonNullable<Long> getUserId() {
        return userId;
    }
    public void setUserId(JsonNullable<Long> userId) {
        this.userId = userId;
    }
}

