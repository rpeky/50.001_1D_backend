package com.travelapp.api.bookmarks.DTO;

public class BookmarksCreateDTO {
    private Long userId;
    private Long activityId;
    // implement itineraryId bookmark if needed
    //private Long itineraryId;

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

    /*
    public Long getItineraryId() {
        return itineraryId;
    }
    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
    }
    */
}

