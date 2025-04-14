package com.travelapp.api.users.DTO;

import com.travelapp.api.status.DTO.external.StatusCreateDTO;

public class UsersCreateDTO {
    private String userUid;
    private String userName;
    private String email;
    private String bio;
    private StatusCreateDTO status;


    public String getUserUid() {
        return userUid;
    }
    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }


    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }


    public StatusCreateDTO getStatus() {
        return status;
    }
    public void setStatus(StatusCreateDTO statusCreateDTO) {
        this.status = statusCreateDTO;
    }

}
