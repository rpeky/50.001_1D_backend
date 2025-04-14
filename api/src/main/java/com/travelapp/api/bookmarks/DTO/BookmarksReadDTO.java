package com.travelapp.api.bookmarks.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.time.LocalDateTime;

public class BookmarksReadDTO {

    private Long bookmarkId;
    private UsersOtherReadDTO createdBy;
    private ActivitiesOtherReadDTO activity;
    private LocalDateTime createdAt;

    public Long getBookmarkId() {
        return bookmarkId;
    }
    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public UsersOtherReadDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersOtherReadDTO createdBy) {
        this.createdBy = createdBy;
    }

    public ActivitiesOtherReadDTO getActivity() {
        return activity;
    }
    public void setActivity(ActivitiesOtherReadDTO activity) {
        this.activity = activity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
