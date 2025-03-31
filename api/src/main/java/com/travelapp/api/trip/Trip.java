package com.travelapp.api.trip;

import com.travelapp.api.activities.Activities;
import com.travelapp.api.itineraries.Itineraries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", nullable = false)
    private Integer tripId;

    // Optional relationship: a trip can be associated with an itinerary.
    @ManyToOne
    @JoinColumn(name = "itinerary_id", referencedColumnName = "itinerary_id", nullable = false)
    private Itineraries itinerary;

    // Optional relationship: a trip can be associated with an activity.
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "activity_id", nullable = false)
    private Activities activity;


    //No-Arg Constructor
    public Trip() {
    }
    //Full-Arg Constructor
    public Trip(Itineraries itinerary, Activities activity) {
        this.itinerary = itinerary;
        this.activity = activity;
    }


    // Getters and setters
    public Integer getTripId() {
        return tripId;
    }
    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }


    public Itineraries getItinerary() {
        return itinerary;
    }
    public void setItinerary(Itineraries itinerary) {
        this.itinerary = itinerary;
    }

    public Activities getActivity() {
        return activity;
    }
    public void setActivity(Activities activity) {
        this.activity = activity;
    }

}
