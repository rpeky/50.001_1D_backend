package com.travelapp.api.itineraries.service;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itinerarydayactivity.daysactivity.entity.DayActivity;
import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;

import java.util.List;

public class GetItineraryPriceRange {

    public static Double calculatePriceRange(Itineraries itinerary) {

        Double total_price = 0.0;

        List<ItineraryDay> timeline = itinerary.getTimeline();

        if (timeline != null) {
            for (ItineraryDay itineraryDay : timeline) {
                List<DayActivity> activities = itineraryDay.getActivities();
                if (activities != null) {
                    for (DayActivity dayActivity : activities) {
                        Activities activity = dayActivity.getActivity();
                        if (activity.getPrice() != null) {
                            total_price = total_price + activity.getPrice();
                        }
                    }
                }
            }
        }
        return total_price;
    }
}
