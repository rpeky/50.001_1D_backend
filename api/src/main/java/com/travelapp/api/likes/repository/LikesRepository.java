package com.travelapp.api.likes.repository;

import com.travelapp.api.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    // Return likes for a given user ID.
    List<Likes> findByUserUserId(Long userId);

    // Return likes for a given activity ID.
    List<Likes> findByActivityActivityId(Long activityId);
    
    // Count likes for a given activity ID.
    long countByActivityActivityId(Long activityId);
    
    // Optionally, count likes for a given itinerary ID.
    // long countByItineraryItineraryId(Long itineraryId);
}

