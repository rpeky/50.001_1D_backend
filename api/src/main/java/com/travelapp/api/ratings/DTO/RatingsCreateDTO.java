package com.travelapp.api.ratings.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherCUDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherCUDTO;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;

public class RatingsCreateDTO {

    private UsersOtherCreateDTO createdBy;
    private ActivitiesOtherCUDTO activity;
    private ItinerariesOtherCUDTO itinerary;
    private Long rating;

    public RatingsCreateDTO() {
    }

    public RatingsCreateDTO(UsersOtherCreateDTO createdBy, ActivitiesOtherCUDTO activity,
                            ItinerariesOtherCUDTO itinerary, Long rating) {
        this.createdBy = createdBy;
        this.activity = activity;
        this.itinerary = itinerary;
        this.rating = rating;
    }

    public UsersOtherCreateDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersOtherCreateDTO createdBy) {
        this.createdBy = createdBy;
    }

    public ActivitiesOtherCUDTO getActivity() {
        return activity;
    }
    public void setActivity(ActivitiesOtherCUDTO activity) {
        this.activity = activity;
    }

    public ItinerariesOtherCUDTO getItinerary() {
        return itinerary;
    }
    public void setItinerary(ItinerariesOtherCUDTO itinerary) {
        this.itinerary = itinerary;
    }

    public Long getRating() {
        return rating;
    }
    public void setRating(Long rating) {
        this.rating = rating;
    }
}
