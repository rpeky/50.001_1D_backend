package com.travelapp.api.graph.controller;

import com.travelapp.api.graph.dto.ActivityRecommendationDTO;
import com.travelapp.api.graph.dto.UserRecommendationDTO;
import com.travelapp.api.graph.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    /**
     * Endpoint for friend recommendations.
     * Example: GET /recommendations/friends?userUid=<currentUserUid>
     */
    @GetMapping("/friends")
    public ResponseEntity<List<UserRecommendationDTO>> recommendFriends(@RequestParam String userUid) {
        List<UserRecommendationDTO> recommendations = recommendationService.recommendFriends(userUid);
        return ResponseEntity.ok(recommendations);
    }

    /**
     * Endpoint for activity recommendations.
     * Example: GET /recommendations/activities?userUid=<currentUserUid>
     */
    @GetMapping("/activities")
    public ResponseEntity<List<ActivityRecommendationDTO>> recommendActivities(@RequestParam String userUid) {
        List<ActivityRecommendationDTO> recommendations = recommendationService.recommendActivities(userUid);
        return ResponseEntity.ok(recommendations);
    }
}
