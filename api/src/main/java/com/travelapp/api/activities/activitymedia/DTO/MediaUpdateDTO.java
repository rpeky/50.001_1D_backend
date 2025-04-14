package com.travelapp.api.activities.activitymedia.DTO;

import org.openapitools.jackson.nullable.JsonNullable;

public class MediaUpdateDTO {
    private JsonNullable<String> type;
    private JsonNullable<String> url;

    public JsonNullable<String> getType() {
        return type;
    }
    public void setType(JsonNullable<String> type) {
        this.type = type;
    }

    public JsonNullable<String> getUrl() {
        return url;
    }
    public void setUrl(JsonNullable<String> url) {
        this.url = url;
    }

}
