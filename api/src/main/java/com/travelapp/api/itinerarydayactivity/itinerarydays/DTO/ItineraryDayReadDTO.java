package com.travelapp.api.itinerarydayactivity.itinerarydays.DTO;

import com.travelapp.api.itinerarydayactivity.daysactivity.DTO.DayActivityReadDTO;

import java.util.List;

public class ItineraryDayReadDTO {

    private Long day;
    private List<DayActivityReadDTO> activities;

    public Long getDay() {
        return day;
    }
    public void setDay(Long day) {
        this.day = day;
    }

    public List<DayActivityReadDTO> getActivities() {
        return activities;
    }
    public void setActivities(List<DayActivityReadDTO> activities) {
        this.activities = activities;
    }
}
