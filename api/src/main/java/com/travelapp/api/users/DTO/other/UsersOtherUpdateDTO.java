package com.travelapp.api.users.DTO.other;

import org.openapitools.jackson.nullable.JsonNullable;

public class UsersOtherUpdateDTO {
    private JsonNullable<String> userUid = JsonNullable.undefined();

    public JsonNullable<String> getUserUid() {
        return userUid;
    }
    public void setUserUid(JsonNullable<String> userUid) {
        this.userUid = userUid;
    }

}
