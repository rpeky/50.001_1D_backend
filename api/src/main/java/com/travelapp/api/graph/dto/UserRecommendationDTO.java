package com.travelapp.api.graph.dto;

public class UserRecommendationDTO {
    private String userUid;
    private String userName;
    private double score;

    public UserRecommendationDTO(String userUid, String userName, double score) {
        this.userUid = userUid;
        this.userName = userName;
        this.score = score;
    }
    
    // Getters and setters
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
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
}

