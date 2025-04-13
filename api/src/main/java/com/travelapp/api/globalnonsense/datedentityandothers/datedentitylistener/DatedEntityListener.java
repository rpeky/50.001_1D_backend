package com.travelapp.api.globalnonsense.datedentityandothers.datedentitylistener;

import com.travelapp.api.globalnonsense.datedentityandothers.CreatedAtTracker;
import com.travelapp.api.globalnonsense.datedentityandothers.DatedEntity;
import com.travelapp.api.globalnonsense.datedentityandothers.ModifiedAtTracker;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


public class DatedEntityListener {
    @PrePersist
    public void setCreatedAt(Object entity) {
        if (entity instanceof DatedEntity) {
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            DatedEntity dated = (DatedEntity) entity;
            dated.setCreatedAt(now);
            dated.setModifiedAt(now);
        }

        if (entity instanceof CreatedAtTracker) {
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            CreatedAtTracker dated = (CreatedAtTracker) entity;
            dated.setCreatedAt(now);
        }
    }

    @PreUpdate
    public void setModifiedAt(Object entity) {
        if (entity instanceof DatedEntity) {
            ((DatedEntity) entity).setModifiedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
        if (entity instanceof ModifiedAtTracker) {
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            ModifiedAtTracker dated = (ModifiedAtTracker) entity;
            dated.setModifiedAt(now);
        }
    }


}