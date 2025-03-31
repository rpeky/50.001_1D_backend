package com.travelapp.api.bookmarks;

import com.travelapp.api.activities.Activities;
import com.travelapp.api.users.Users;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Bookmarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long bookmarkId;

    //owner of rel. with users (fk)
    @ManyToOne
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users createdBy;

    //owner of rel. with activities (fk)
    @OneToOne
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "user_id", nullable = false)
    private Activities activity;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;


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
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
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
