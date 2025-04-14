package com.travelapp.api.AAsecurity.DTO;

public class LoginRequestDTO {

    private String userUid;
    private String email;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

