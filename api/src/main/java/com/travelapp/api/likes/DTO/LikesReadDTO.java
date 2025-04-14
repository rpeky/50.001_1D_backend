package com.travelapp.api.likes.DTO;

import com.travelapp.api.activities.DTO.ActivitiesReadDTO;
import com.travelapp.api.activities.DTO.other.ActivitiesOtherReadDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherReadDTO;

public class LikesReadDTO {

    private Long likeId;
    private ActivitiesOtherReadDTO activity;
    private ItinerariesOtherReadDTO itinerary;

    public Long getLikeId() {
        return likeId;
    }
    public void setLikeId(Long likeId) {
        this.likeId = likeId;
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
}
