package com.travelapp.api.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    // return a list of comments that user's ID == userId has commented on
    List<Comments> findByCreatedByUserId(Long userId);

    // return a list of comments that activity ID == activityId has comments on
    List<Comments> findByActivityActivityId(Long activityId);

    // return a list of comments that itinerary ID == itineraryId has comments on
    List<Comments> findByItineraryItineraryId(Long itineraryId);
}
