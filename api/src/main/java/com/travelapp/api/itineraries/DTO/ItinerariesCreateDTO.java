package com.travelapp.api.itineraries.DTO;

import com.travelapp.api.itinerarydayactivity.itinerarydays.DTO.ItineraryDayCUDTO;
import com.travelapp.api.status.DTO.external.StatusCreateDTO;
import com.travelapp.api.users.DTO.other.UserOtherCreateDTO;

import java.util.List;

public class ItinerariesCreateDTO {
    private String title;
    private UserOtherCreateDTO createdBy;
    private String description;
    private Double priceRange;
    private List<ItineraryDayCUDTO> timeline;
    private String thumbnail;
    private String coverPhoto;
    private StatusCreateDTO status;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public UserOtherCreateDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UserOtherCreateDTO createdBy) {
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

    public List<ItineraryDayCUDTO> getTimeline() {
        return timeline;
    }
    public void setTimeline(List<ItineraryDayCUDTO> timeline) {
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

    public StatusCreateDTO getStatus() {
        return status;
    }
    public void setStatus(StatusCreateDTO status) {
        this.status = status;
    }
}
