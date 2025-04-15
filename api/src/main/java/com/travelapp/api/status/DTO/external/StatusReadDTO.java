package com.travelapp.api.status.DTO.external;

import java.time.LocalDateTime;

public class StatusReadDTO {
    private Long statusId;
    private Long statusName;

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getStatusName() {
        return statusName;
    }

    public void setStatusName(Long statusName) {
        this.statusName = statusName;
    }
}

