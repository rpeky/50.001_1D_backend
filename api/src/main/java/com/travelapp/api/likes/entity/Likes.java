package com.travelapp.api.likes.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.users.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes extends DatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false)
    private Long likeId;

    //owner of rel. with users (fk)
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users createdBy;

    //owner of rel. with activity (fk)
    @ManyToOne
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id", nullable = true)
    private Activities activity;

    //owner of rel. with itinerary (fk)
    @ManyToOne
    @JoinColumn(name = "fk_itinerary_id", referencedColumnName = "itinerary_id", nullable = true)
    private Itineraries itinerary;


    //Constructors
    // No-Args Constructor
    public Likes() {
    }
    // Full-Arg constructor
    public Likes(Long likeId, Users createdBy, Activities activity, Itineraries itinerary) {
        this.likeId = likeId;
        this.createdBy = createdBy;
        this.activity = activity;
        this.itinerary = itinerary;
    }

    public Likes(Users user, Activities activity) {
        this.createdBy = user;
        this.activity = activity;
    }

    public Likes(Users user, Itineraries itinerary) {
        this.createdBy = user;
        this.itinerary = itinerary;
    }


    // Getters and Setters
    public Long getLikeId() {
        return likeId;
    }
    public void setLikeId(Long likeId) {
        this.likeId = likeId;
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


    public Itineraries getItinerary() {
        return itinerary;
    }
    public void setItinerary(Itineraries itinerary) {
        this.itinerary = itinerary;
    }



}
