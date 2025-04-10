package com.travelapp.api.itinerarydayactivity.itinerarydays.DTO;

import com.travelapp.api.itinerarydayactivity.daysactivity.DTO.DayActivityCUDTO;

import java.util.List;

public class ItineraryDayCUDTO {

    private Long day;
    private List<DayActivityCUDTO> activities;

    public List<DayActivityCUDTO> getActivities() {
        return activities;
    }
    public void setActivities(List<DayActivityCUDTO> activities) {
        this.activities = activities;
    }

    public Long getDay() {
        return day;
    }
    public void setDay(Long day) {
        this.day = day;
    }


}
