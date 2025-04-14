package com.travelapp.api.activities.DTO;

import com.travelapp.api.activities.activitymedia.DTO.MediaReadDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.status.DTO.external.StatusReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ActivitiesReadDTO {
    private Long activityId;
    private String title;
    private UsersOtherReadDTO createdBy;
    private String description;
    private String location;
    private String locationLink;
    private Double price;
    private String thumbnail;
    private List<MediaReadDTO> medias;
    private Long likes;
    private Double ratings;
    private List<CommentsReadDTO> comments;
    private StatusReadDTO status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

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
