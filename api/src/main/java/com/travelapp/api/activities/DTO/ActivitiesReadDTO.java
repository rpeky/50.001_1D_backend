package com.travelapp.api.activities.DTO;

import com.travelapp.api.users.DTO.UserActivityReadDTO;

public class ActivitiesReadDTO {
    private Long activityId;
    private String title;
    private UserActivityReadDTO createdBy;
    private String description;
    private String location;
    private String locationLink;
    private Double price;
    private String thumbnail;

    public Long getActivityId() {
        return this.activityId;
    }
    public void setActivityId(Long ActivityId) {
        this.activityId = ActivityId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public UserActivityReadDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UserActivityReadDTO createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationLink() {
        return locationLink;
    }
    public void setLocationLink(String locationLink) {
        this.locationLink = locationLink;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
