package com.travelapp.api.globalnonsense.datedentityandothers;

import com.travelapp.api.globalnonsense.datedentityandothers.datedentitylistener.DatedEntityListener;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(DatedEntityListener.class)
public class CreatedAtTracker {

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
