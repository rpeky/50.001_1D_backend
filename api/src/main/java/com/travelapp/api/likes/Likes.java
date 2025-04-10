package com.travelapp.api.likes;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.globalnonsense.datedentity.DatedEntity;
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
    private Integer likeId;

    //owner of rel. with users (fk)
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users user;

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
    public Likes(Integer likeId, Users user, Activities activity, Itineraries itinerary) {
        this.likeId = likeId;
        this.user = user;
        this.activity = activity;
        this.itinerary = itinerary;
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



}
