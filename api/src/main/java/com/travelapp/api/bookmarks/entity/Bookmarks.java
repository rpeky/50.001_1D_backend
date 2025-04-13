package com.travelapp.api.bookmarks.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.globalnonsense.datedentityandothers.CreatedAtTracker;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.users.entity.Users;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookmarks")
public class Bookmarks extends CreatedAtTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long bookmarkId;

    //owner of rel. with users (fk)
    @ManyToOne
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users createdBy;

    //owner of rel. with activities (fk)
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id", nullable = false)
    private Activities activity;


    //Constructors
    //No-Arg Constructor
    public Bookmarks() {
    }
    //Full-Arg Constructor
    public Bookmarks(Long bookmarkId, Users createdBy, Activities activity,
                     LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.bookmarkId = bookmarkId;
        this.createdBy = createdBy;
        this.activity = activity;
    }

    public Bookmarks(Activities activity, Users createdBy) {
        this.activity = activity;
        this.createdBy = createdBy;
    }

    //Getters and Setters
    public Long getBookmarkId() {
        return bookmarkId;
    }
    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }


    public Users getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }


    public Activities getActivity() {
        return activity;
    }
    public void setActivity(Activities activity) {
        this.activity = activity;
    }


}
