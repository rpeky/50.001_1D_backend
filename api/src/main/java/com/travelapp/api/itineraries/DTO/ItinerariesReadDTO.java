package com.travelapp.api.itineraries.DTO;

import com.travelapp.api.status.DTO.external.StatusReadDTO;
import com.travelapp.api.itinerarydayactivity.itinerarydays.DTO.ItineraryDayReadDTO;
import com.travelapp.api.users.DTO.other.UserOtherReadDTO;

import java.util.List;

public class ItinerariesReadDTO {

    private Long itineraryId;
    private String title;
    private UserOtherReadDTO createdBy;
    private String description;
    private Double priceRange;
    private Long duration;
    private List<ItineraryDayReadDTO> timeline;
    private String thumbnail;
    private String coverPhoto;
    private StatusReadDTO status;

    public Long getItineraryId() {
        return itineraryId;
    }
    public void setItineraryId(Long itineraryId) {
        this.itineraryId = itineraryId;
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

    public Double getPriceRange() {
        return priceRange;
    }
    public void setPriceRange(Double priceRange) {
        this.priceRange = priceRange;
    }

    public Long getDuration() {
        return duration;
    }
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public List<ItineraryDayReadDTO> getTimeline() {
        return timeline;
    }
    public void setTimeline(List<ItineraryDayReadDTO> timeline) {
        this.timeline = timeline;
    }

    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }
    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public StatusReadDTO getStatus() {
        return status;
    }
    public void setStatus(StatusReadDTO status) {
        this.status = status;
    }
}
