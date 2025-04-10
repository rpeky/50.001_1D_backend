package com.travelapp.api.users.DTO;

import com.travelapp.api.activities.DTO.other.ActivitiesOtherReadDTO;
import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherReadDTO;

import java.time.LocalDateTime;
import java.util.List;

public class UsersReadSelfDTO {
    private String userUid;
    private String userName;
    private String email;
    private String bio;
    private List<ActivitiesOtherReadDTO> activities;
    private List<ItinerariesOtherReadDTO> myTrips;
    private List<ItinerariesOtherReadDTO> itineraries;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

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

    public List<ActivitiesOtherReadDTO> getActivities() {
        return activities;
    }
    public void setActivities(List<ActivitiesOtherReadDTO> activities) {
        this.activities = activities;
    }

    public List<ItinerariesOtherReadDTO> getMyTrips() {
        return myTrips;
    }
    public void setMyTrips(List<ItinerariesOtherReadDTO> myTrips) {
        this.myTrips = myTrips;
    }

    public List<ItinerariesOtherReadDTO> getItineraries() {
        return itineraries;
    }
    public void setItineraries(List<ItinerariesOtherReadDTO> itineraries) {
        this.itineraries = itineraries;
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
