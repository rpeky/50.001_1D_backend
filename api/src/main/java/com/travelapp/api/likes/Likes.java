package com.travelapp.api.likes;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.itineraries.Itineraries;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private Integer likeId;

    //owner of rel. with users (fk)
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users user;

    //owner of rel. with activity (fk)
    @ManyToOne
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id")
    private Activities activity;

    //owner of rel. with itinerary (fk)
    @OneToOne
    @JoinColumn(name = "fk_itinerary_id", referencedColumnName = "itinerary_id")
    private Itineraries itinerary;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;


    //Constructors
    // No-Args Constructor
    public Likes() {
    }
    // Full-Arg constructor
    public Likes(Integer likeId, Users user, Activities activity, Itineraries itinerary,
                 LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.likeId = likeId;
        this.user = user;
        this.activity = activity;
        this.itinerary = itinerary;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    // Getters and Setters
    public Integer getLikeId() {
        return likeId;
    }
    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }


    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }


    public Activities getActivity() {
        return activity;
    }
    public void setActivity(Activities activity) {
        this.activity = activity;
    }


    public Itineraries getItinerary() {
        return itinerary;
    }
    public void setItinerary(Itineraries itinerary) {
        this.itinerary = itinerary;
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
