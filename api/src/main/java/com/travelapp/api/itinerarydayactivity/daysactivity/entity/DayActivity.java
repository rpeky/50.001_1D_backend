package com.travelapp.api.itinerarydayactivity.daysactivity.entity;

import com.travelapp.api.activities.entity.Activities;
import com.travelapp.api.globalnonsense.datedentity.DatedEntity;
import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "day_activity")
public class DayActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_activity_id", nullable = false)
    private Long daysActivityId;

    @ManyToOne
    @JoinColumn(name = "fk_day_id", referencedColumnName = "day_id", nullable = false)
    private ItineraryDay day;

    @ManyToOne
    @JoinColumn(name = "fk_activity_id", referencedColumnName = "activity_id", nullable = false)
    private Activities activity;

    public DayActivity() {
    }

    public DayActivity(Long daysActivityId, ItineraryDay day, Activities activity) {
        this.daysActivityId = daysActivityId;
        this.day = day;
        this.activity = activity;
    }

    public Long getDaysActivityId() {
        return daysActivityId;
    }
    public void setDaysActivityId(Long daysActivityId) {
        this.daysActivityId = daysActivityId;
    }

    public ItineraryDay getDay() {
        return day;
    }
    public void setDay(ItineraryDay day) {
        this.day = day;
    }

    public Activities getActivity() {
        return activity;
    }
    public void setActivity(Activities activity) {
        this.activity = activity;
    }



}
