package com.travelapp.api.itinerarydayactivity.itinerarydays.entity;

import com.travelapp.api.globalnonsense.datedentity.DatedEntity;
import com.travelapp.api.itineraries.entity.Itineraries;
import com.travelapp.api.itinerarydayactivity.daysactivity.entity.DayActivity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "itinerary_day")
public class ItineraryDay {

    @ManyToOne
    @JoinColumn(name = "fk_itinerary_id", referencedColumnName = "itinerary_id", nullable = true)
    private Itineraries itinerary;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id", nullable = false)
    private Long dayId;

    @Column(name = "day", nullable = false)
    private Long day;

    @OneToMany(mappedBy = "day", targetEntity = DayActivity.class,
            cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DayActivity> activities;

    public ItineraryDay() {
    }

    public ItineraryDay(Itineraries itinerary, Long dayId, Long day, List<DayActivity> activities) {
        this.itinerary = itinerary;
        this.dayId = dayId;
        this.day = day;
        this.activities = activities;
    }

    public Itineraries getItinerary() {
        return itinerary;
    }
    public void setItinerary(Itineraries itinerary) {
        this.itinerary = itinerary;
    }

    public Long getDayId() {
        return dayId;
    }
    public void setDayId(Long dayId) {
        this.dayId = dayId;
    }

    public Long getDay() {
        return day;
    }
    public void setDay(Long day) {
        this.day = day;
    }

    public List<DayActivity> getActivities() {
        return activities;
    }
    public void setActivities(List<DayActivity> activities) {
        this.activities = activities;
        this.activities.forEach(dayActivity -> dayActivity.setDay(this));
    }


}
