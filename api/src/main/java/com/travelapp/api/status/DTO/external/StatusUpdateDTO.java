package com.travelapp.api.status.DTO.external;

import org.openapitools.jackson.nullable.JsonNullable;

public class StatusUpdateDTO {
    private JsonNullable<Long> statusId;

    public JsonNullable<Long> getStatusId() {
        return statusId;
    }
    public void setStatusId(JsonNullable<Long> statusId) {
        this.statusId = statusId;
    }

}
