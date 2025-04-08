package com.travelapp.api.status.DTO.external;

import java.time.LocalDateTime;
//import java.util.Optional;

public class StatusReadDTO {
    private String statusName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

