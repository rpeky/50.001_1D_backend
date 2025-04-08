package com.travelapp.api.status.DTO.internal;

public class StatusUpdateDTOInternal {
    private Long statusId;
    private String statusName;


    public Long getStatusId() {
        return statusId;
    }
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
