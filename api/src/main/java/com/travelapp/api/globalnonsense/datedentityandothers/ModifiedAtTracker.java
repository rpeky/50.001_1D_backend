package com.travelapp.api.globalnonsense.datedentityandothers;

import com.travelapp.api.globalnonsense.datedentityandothers.datedentitylistener.DatedEntityListener;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(DatedEntityListener.class)
public class ModifiedAtTracker {

    @Column(name = "modified_at", nullable = true)
    private LocalDateTime modifiedAt;

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
