package com.travelapp.api.comments.repository;

import com.travelapp.api.comments.entity.Comments;
import com.travelapp.api.users.entity.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    // Return a list of comments created by the given user (using internal numeric ID)
    List<Comments> findByCreatedByUserId(Long userId);

    // Return a list of comments for a specific activity
    List<Comments> findByActivityActivityId(Long activityId);

    // Return a list of comments for a specific itinerary
    List<Comments> findByItineraryItineraryId(Long itineraryId);
    
    // Count comments by activity
    long countByActivityActivityId(Long activityId);
}
