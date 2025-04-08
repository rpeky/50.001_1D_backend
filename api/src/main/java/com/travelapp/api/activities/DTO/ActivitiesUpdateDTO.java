package com.travelapp.api.activities.DTO;

import com.travelapp.api.users.DTO.UserActivityReadDTO;
import com.travelapp.api.users.DTO.UserActivityUpdateDTO;

import org.openapitools.jackson.nullable.JsonNullable;

public class ActivitiesUpdateDTO {
    private JsonNullable<Long> activityId = JsonNullable.undefined();
    private JsonNullable<String> title = JsonNullable.undefined();
    private JsonNullable<UserActivityUpdateDTO> createdBy = JsonNullable.undefined();
    private JsonNullable<String> description = JsonNullable.undefined();
    private JsonNullable<String> location = JsonNullable.undefined();
    private JsonNullable<String> locationLink = JsonNullable.undefined();
    private JsonNullable<Double> price = JsonNullable.undefined();
    private JsonNullable<String> thumbnail = JsonNullable.undefined();


    public JsonNullable<Long> getActivityId() {
        return activityId;
    }
    public void setActivityId(JsonNullable<Long> activityId) {
        this.activityId = activityId;
    }

    public JsonNullable<String> getTitle() {
        return title;
    }
    public void setTitle(JsonNullable<String> title) {
        this.title = title;
    }

    public JsonNullable<UserActivityUpdateDTO> getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(JsonNullable<UserActivityUpdateDTO> createdBy) {
        this.createdBy = createdBy;
    }

    public JsonNullable<String> getDescription() {
        return description;
    }
    public void setDescription(JsonNullable<String> description) {
        this.description = description;
    }

    public JsonNullable<String> getLocation() {
        return location;
    }
    public void setLocation(JsonNullable<String> location) {
        this.location = location;
    }

    public JsonNullable<String> getLocationLink() {
        return locationLink;
    }
    public void setLocationLink(JsonNullable<String> locationLink) {
        this.locationLink = locationLink;
    }

    public JsonNullable<Double> getPrice() {
        return price;
    }
    public void setPrice(JsonNullable<Double> price) {
        this.price = price;
    }

    public JsonNullable<String> getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(JsonNullable<String> thumbnail) {
        this.thumbnail = thumbnail;
    }
}
