package com.travelapp.api.itineraries.DTO;

import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.status.DTO.external.StatusReadDTO;
import com.travelapp.api.itinerarydayactivity.itinerarydays.DTO.ItineraryDayReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ItinerariesReadDTO {

    private Long itineraryId;
    private String title;
    private UsersOtherReadDTO createdBy;
    private String description;
    private Double priceRange;
    private Long likes;
    private Double ratings;
    private Long duration;
    private List<ItineraryDayReadDTO> timeline;
    private String thumbnail;
    private String coverPhoto;
    private List<CommentsReadDTO> comments;
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

    public UsersOtherReadDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersOtherReadDTO createdBy) {
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

    public Long getLikes() {
        return likes;
    }
    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Double getRatings() {
        return ratings;
    }
    public void setRatings(Double ratings) {
        this.ratings = ratings;
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

    public List<CommentsReadDTO> getComments() {
        return comments;
    }
    public void setComments(List<CommentsReadDTO> comments) {
        this.comments = comments;
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
