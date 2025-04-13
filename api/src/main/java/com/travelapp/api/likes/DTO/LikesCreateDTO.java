package com.travelapp.api.likes.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherCUDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherCUDTO;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;

public class LikesCreateDTO {
    private UsersOtherCreateDTO createdBy;
    private ActivitiesOtherCUDTO activity;
    private ItinerariesOtherCUDTO itinerary;

    public LikesCreateDTO() {
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
}
