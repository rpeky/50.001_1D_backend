package com.travelapp.api.bookmarks.DTO;

import java.time.LocalDateTime;

public class BookmarksReadDTO {
    private Long bookmarkId;
    private Long userId;
    private Long activityId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Long getBookmarkId() {
        return bookmarkId;
    }
    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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

