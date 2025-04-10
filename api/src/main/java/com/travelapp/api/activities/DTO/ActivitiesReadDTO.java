package com.travelapp.api.activities.DTO;

import com.travelapp.api.activities.activitymedia.DTO.MediaReadDTO;
import com.travelapp.api.status.DTO.external.StatusReadDTO;
import com.travelapp.api.users.DTO.other.UserOtherReadDTO;

import java.util.List;

public class ActivitiesReadDTO {
    private Long activityId;
    private String title;
    private UserOtherReadDTO createdBy;
    private String description;
    private String location;
    private String locationLink;
    private Double price;
    private String thumbnail;
    private List<MediaReadDTO> medias;
    private StatusReadDTO status;

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

    public UserOtherReadDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UserOtherReadDTO createdBy) {
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

    public List<MediaReadDTO> getMedias() {
        return medias;
    }
    public void setMedias(List<MediaReadDTO> medias) {
        this.medias = medias;
    }

    public StatusReadDTO getStatus() {
        return status;
    }
    public void setStatus(StatusReadDTO status) {
        this.status = status;
    }
}
