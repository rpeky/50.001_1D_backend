package com.travelapp.api.status.DTO.external;

import org.openapitools.jackson.nullable.JsonNullable;

public class StatusUpdateDTO {
    private JsonNullable<String> statusName;

    public JsonNullable<String> getStatusName() {
        return statusName;
    }
    public void setStatusName(JsonNullable<String> statusName) {
        this.statusName = statusName;
    }

}
