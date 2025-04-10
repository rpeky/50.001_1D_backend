package com.travelapp.api.comments;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.users.entity.Users;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment")
    private String comment;

    //owner of rel. with activity (fk)
    @ManyToOne
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id", nullable = true)
    private Activities activity;

    //owner of rel. with itineraries (fk)
    @ManyToOne
    @JoinColumn(name = "fk_itinerary_id", referencedColumnName = "itinerary_id", nullable = true)
    private Itineraries itinerary;

    //owner of rel. with users (fk)
    @ManyToOne
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;


    //Constructors
    //No-Arg Constructor
    public Comments() {
    }
    //Full-Arg Constructor
    public Comments(Long commentId, String comment,
                    Activities activity, Itineraries itineraries, Users createdBy,
                    LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.commentId = commentId;
        this.comment = comment;
        this.activity = activity;
        this.itinerary = itineraries;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    //Setters and Getters
    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }


    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }


    public Activities getActivity() {
        return activity;
    }
    public void setActivity(Activities activity) {
        this.activity = activity;
    }


    public Itineraries getItineraries() {
        return itinerary;
    }
    public void setItineraries(Itineraries itineraries) {
        this.itinerary = itineraries;
    }


    public Users getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
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
