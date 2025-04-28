package com.travelapp.api.graph.dto;

public class ActivityRecommendationDTO {
    private Long activityId;
    private String title;
    private double score;

    public ActivityRecommendationDTO(Long activityId, String title, double score) {
        this.activityId = activityId;
        this.title = title;
        this.score = score;
    }

    // Getters and setters
    public Long getActivityId() {
        return activityId;
    }
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
}
