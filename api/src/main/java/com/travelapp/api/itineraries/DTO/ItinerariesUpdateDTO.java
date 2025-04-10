package com.travelapp.api.itineraries.DTO;

import com.travelapp.api.itinerarydayactivity.itinerarydays.DTO.ItineraryDayCUDTO;
import com.travelapp.api.status.DTO.external.StatusUpdateDTO;
import com.travelapp.api.users.DTO.other.UserOtherUpdateDTO;

import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

public class ItinerariesUpdateDTO {
    private JsonNullable<Long> itineraryId = JsonNullable.undefined();
    private JsonNullable<String> title = JsonNullable.undefined();
    private JsonNullable<UserOtherUpdateDTO> createdBy = JsonNullable.undefined();
    private JsonNullable<String> description = JsonNullable.undefined();
    private JsonNullable<Double> priceRange = JsonNullable.undefined();
    private JsonNullable<List<ItineraryDayCUDTO>> timeline = JsonNullable.undefined();
    private JsonNullable<String> thumbnail = JsonNullable.undefined();
    private JsonNullable<String> coverPhoto = JsonNullable.undefined();
    private JsonNullable<StatusUpdateDTO> status = JsonNullable.undefined();

    public JsonNullable<Long> getItineraryId() {
        return itineraryId;
    }
    public void setItineraryId(JsonNullable<Long> itineraryId) {
        this.itineraryId = itineraryId;
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

    public JsonNullable<Double> getPriceRange() {
        return priceRange;
    }
    public void setPriceRange(JsonNullable<Double> priceRange) {
        this.priceRange = priceRange;
    }

    public JsonNullable<List<ItineraryDayCUDTO>> getTimeline() {
        return timeline;
    }
    public void setTimeline(JsonNullable<List<ItineraryDayCUDTO>> timeline) {
        this.timeline = timeline;
    }

    public JsonNullable<String> getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(JsonNullable<String> thumbnail) {
        this.thumbnail = thumbnail;
    }

    public JsonNullable<String> getCoverPhoto() {
        return coverPhoto;
    }
    public void setCoverPhoto(JsonNullable<String> coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public JsonNullable<StatusUpdateDTO> getStatus() {
        return status;
    }
    public void setStatus(JsonNullable<StatusUpdateDTO> status) {
        this.status = status;
    }
}
