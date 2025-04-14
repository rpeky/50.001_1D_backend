package com.travelapp.api.ratings.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherReadDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

public class RatingsReadDTO {

    private Long ratingId;
    private UsersOtherReadDTO createdBy;
    private ActivitiesOtherReadDTO activity;
    private ItinerariesOtherReadDTO itinerary;
    private Long rating;

    public RatingsReadDTO() {
    }

    public RatingsReadDTO(Long ratingId, UsersOtherReadDTO createdBy, ActivitiesOtherReadDTO activity,
                          ItinerariesOtherReadDTO itinerary, Long rating) {
        this.ratingId = ratingId;
        this.createdBy = createdBy;
        this.activity = activity;
        this.itinerary = itinerary;
        this.rating = rating;
    }

    public Long getRatingId() {
        return ratingId;
    }
    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
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

    public ItinerariesOtherReadDTO getItinerary() {
        return itinerary;
    }
    public void setItinerary(ItinerariesOtherReadDTO itinerary) {
        this.itinerary = itinerary;
    }

    public Long getRating() {
        return rating;
    }
    public void setRating(Long rating) {
        this.rating = rating;
    }
}
