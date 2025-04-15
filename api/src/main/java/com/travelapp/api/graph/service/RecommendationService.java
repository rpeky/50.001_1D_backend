package com.travelapp.api.graph.service;

import com.travelapp.api.graph.service.UserGraphService;
import com.travelapp.api.graph.dto.ActivityRecommendationDTO;
import com.travelapp.api.graph.dto.UserRecommendationDTO;
import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.repository.UsersRepository;
import com.travelapp.api.likes.entity.Likes;  // Assume this is your Likes entity.
import com.travelapp.api.likes.repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RecommendationService {

    @Autowired
    private UserGraphService userGraphService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LikesRepository likesRepository;

    /**
     * Recommend friends based on degree of separation:
     * - Only consider nodes at degree 2 or 3.
     * - A simple score is computed as 1/degree.
     */
    public List<UserRecommendationDTO> recommendFriends(String userUid) {
        int maxDegree = 3;
        Map<String, Integer> degrees = userGraphService.getDegreesOfSeparation(userUid, maxDegree);
        Set<String> directConnections = userGraphService.getGraph().getOrDefault(userUid, Collections.emptySet());
        List<UserRecommendationDTO> recommendations = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : degrees.entrySet()) {
            String candidateUid = entry.getKey();
            int degree = entry.getValue();
            if (candidateUid.equals(userUid) || directConnections.contains(candidateUid) || degree == 1) {
                continue;
            }
            double score = 1.0 / degree;
            Optional<Users> candidateOpt = usersRepository.findByUserUid(candidateUid);
            String candidateName = candidateOpt.map(Users::getUserName).orElse("Unknown");
            recommendations.add(new UserRecommendationDTO(candidateUid, candidateName, score));
        }
        recommendations.sort((r1, r2) -> Double.compare(r2.getScore(), r1.getScore()));
        return recommendations;
    }

    /**
     * Recommend activities based on likes from users within a certain degree.
     * Weight each like by 1/degree, then sum for each activity.
     */
    public List<ActivityRecommendationDTO> recommendActivities(String userUid) {
        int maxDegree = 3;
        Map<String, Integer> degrees = userGraphService.getDegreesOfSeparation(userUid, maxDegree);
        List<Likes> allLikes = likesRepository.findAll();
        Map<Long, Double> activityScores = new HashMap<>();
        Map<Long, String> activityTitles = new HashMap<>();

        for (Likes like : allLikes) {
            String likerUid = like.getCreatedBy().getUserUid();
            if (degrees.containsKey(likerUid)) {
                int degree = degrees.get(likerUid);
                if (degree >= 1 && degree <= maxDegree) {
                    double weight = 1.0 / degree;
                    Long activityId = like.getActivity().getActivityId();
                    activityScores.put(activityId, activityScores.getOrDefault(activityId, 0.0) + weight);
                    if (!activityTitles.containsKey(activityId)) {
                        activityTitles.put(activityId, "Activity " + activityId);
                    }
                }
            }
        }

        List<ActivityRecommendationDTO> recommendations = new ArrayList<>();
        for (Map.Entry<Long, Double> entry : activityScores.entrySet()) {
            recommendations.add(new ActivityRecommendationDTO(entry.getKey(), activityTitles.get(entry.getKey()), entry.getValue()));
        }
        recommendations.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        return recommendations;
    }
}

