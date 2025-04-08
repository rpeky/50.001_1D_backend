package com.travelapp.api.users.DTO;

import org.openapitools.jackson.nullable.JsonNullable;

public class UsersUpdateDTO {
    private JsonNullable<String> userUid = JsonNullable.undefined();
    private JsonNullable<String> userName = JsonNullable.undefined();
    private JsonNullable<String> email = JsonNullable.undefined();
    private JsonNullable<String> bio = JsonNullable.undefined();


    public JsonNullable<String> getUserUid() {
        return userUid;
    }
    public void setUserUid(JsonNullable<String> userUid) {
        this.userUid = userUid;
    }

    public JsonNullable<String> getUserName() {
        return userName;
    }
    public void setUserName(JsonNullable<String> userName) {
        this.userName = userName;
    }

    public JsonNullable<String> getEmail() {
        return email;
    }
    public void setEmail(JsonNullable<String> email) {
        this.email = email;
    }

    public JsonNullable<String> getBio() {
        return bio;
    }
    public void setBio(JsonNullable<String> bio) {
        this.bio = bio;
    }



}
