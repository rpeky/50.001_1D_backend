package com.travelapp.api.globalnonsense.datedentity.datedentitylistener;

import com.travelapp.api.globalnonsense.datedentity.DatedEntity;
import com.travelapp.api.itinerarydayactivity.daysactivity.entity.DayActivity;
import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


public class DatedEntityListener {
    @PrePersist
    public void setCreatedAt(Object entity) {
        if (entity instanceof DatedEntity) {
            ((DatedEntity) entity).setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
    }

    @PreUpdate
    public void setModifiedAt(Object entity) {
        if (entity instanceof DatedEntity) {
            ((DatedEntity) entity).setModifiedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
    }


}