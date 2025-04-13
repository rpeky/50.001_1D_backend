package com.travelapp.api.comments.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherCUDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherCUDTO;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;

public class CommentsCreateDTO {
    private UsersOtherCreateDTO createdBy;
    private ActivitiesOtherCUDTO activity;
    private ItinerariesOtherCUDTO itinerary;
    private String comment;

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

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}
