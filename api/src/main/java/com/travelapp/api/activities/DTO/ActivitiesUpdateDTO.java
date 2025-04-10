package com.travelapp.api.activities.DTO;

import com.travelapp.api.activities.activitymedia.DTO.MediaUpdateDTO;
import com.travelapp.api.status.DTO.external.StatusUpdateDTO;
import com.travelapp.api.users.DTO.other.UserOtherUpdateDTO;

import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

public class ActivitiesUpdateDTO {
    private JsonNullable<Long> activityId = JsonNullable.undefined();
    private JsonNullable<String> title = JsonNullable.undefined();
    private JsonNullable<UserOtherUpdateDTO> createdBy = JsonNullable.undefined();
    private JsonNullable<String> description = JsonNullable.undefined();
    private JsonNullable<String> location = JsonNullable.undefined();
    private JsonNullable<String> locationLink = JsonNullable.undefined();
    private JsonNullable<Double> price = JsonNullable.undefined();
    private JsonNullable<String> thumbnail = JsonNullable.undefined();
    private JsonNullable<List<MediaUpdateDTO>> medias = JsonNullable.undefined();
    private JsonNullable<StatusUpdateDTO> status = JsonNullable.undefined();


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

    public JsonNullable<UserOtherUpdateDTO> getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(JsonNullable<UserOtherUpdateDTO> createdBy) {
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

    public JsonNullable<List<MediaUpdateDTO>> getMedias() {
        return medias;
    }
    public void setMedias(JsonNullable<List<MediaUpdateDTO>> medias) {
        this.medias = medias;
    }

    public JsonNullable<StatusUpdateDTO> getStatus() {
        return status;
    }
    public void setStatus(JsonNullable<StatusUpdateDTO> status) {
        this.status = status;
    }
}
