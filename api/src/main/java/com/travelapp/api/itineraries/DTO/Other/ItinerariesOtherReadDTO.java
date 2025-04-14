package com.travelapp.api.itineraries.DTO.Other;

import com.travelapp.api.status.DTO.external.StatusReadDTO;

import java.time.LocalDateTime;


public class ItinerariesOtherReadDTO {

    private Long itineraryId;
    private String title;
    private String description;
    private String thumbnail;
    private StatusReadDTO status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public StatusReadDTO getStatus() {
        return status;
    }
    public void setStatus(StatusReadDTO status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
