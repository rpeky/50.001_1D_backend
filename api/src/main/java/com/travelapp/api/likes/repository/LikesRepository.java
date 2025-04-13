package com.travelapp.api.likes.repository;

import com.travelapp.api.likes.entity.Likes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByCreatedBy_UserUidAndActivity_ActivityId(String userUid, Long activityId);
    Optional<Likes> findByCreatedBy_UserUidAndItinerary_ItineraryId(String userUid, Long itineraryId);
}
