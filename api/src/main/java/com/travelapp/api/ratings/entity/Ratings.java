package com.travelapp.api.ratings.entity;

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
@Table(name = "ratings")
public class Ratings extends DatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long ratingId;

    //owner of rel. with users (fk)
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_created_by", referencedColumnName = "user_id", nullable = false)
    private Users createdBy;

    //owner of rel. with activity (fk)
    @ManyToOne
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id", nullable = true)
    private Activities activity;

    //owner of rel. with itineraries (fk)
    @ManyToOne
    @JoinColumn(name = "fk_itinerary_id", referencedColumnName = "itinerary_id", nullable = true)
    private Itineraries itinerary;

    @Column(name = "rating", nullable = false)
    private Long rating;

    //Constructors
    public Ratings() {}

    public Ratings(Long ratingId, Long rating, Activities activity, Itineraries itinerary, Users createdBy) {
        this.ratingId = ratingId;
        this.rating = rating;
        this.activity = activity;
        this.itinerary = itinerary;
        this.createdBy = createdBy;
    }

    public Ratings(Users createdBy, Activities activity, Long rating) {
        this.createdBy = createdBy;
        this.activity = activity;
        this.rating = rating;
    }

    public Ratings(Users createdBy, Itineraries itinerary, Long rating) {
        this.createdBy = createdBy;
        this.itinerary = itinerary;
        this.rating = rating;
    }

    //Getters and Setters
    public Long getRatingId() {
        return ratingId;
    }
    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Long getRating() {
        return rating;
    }
    public void setRating(Long rating) {
        this.rating = rating;
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

    public Users getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }
}
