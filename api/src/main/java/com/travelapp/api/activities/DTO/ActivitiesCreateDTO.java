package com.travelapp.api.activities.DTO;

import com.travelapp.api.status.DTO.external.StatusCreateDTO;
import com.travelapp.api.users.DTO.UserActivityCreateDTO;

public class ActivitiesCreateDTO {
    private String title;
    private UserActivityCreateDTO createdBy;
    private String description;
    private String location;
    private String locationLink;
    private Double price;
    private String thumbnail;
    private StatusCreateDTO status;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public UserActivityCreateDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UserActivityCreateDTO createdBy) {
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

    public StatusCreateDTO getStatus() {
        return status;
    }
    public void setStatus(StatusCreateDTO status) {
        this.status = status;
    }
}
