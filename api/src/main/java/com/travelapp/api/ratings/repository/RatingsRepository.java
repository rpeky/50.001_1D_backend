package com.travelapp.api.ratings.repository;

import com.travelapp.api.ratings.entity.Ratings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Ratings, Long> {

    @Query("SELECT r FROM Ratings r WHERE r.activity IS NULL")
    List<Ratings> findAllWhereActivityIsNull();

    @Query("SELECT r FROM Ratings r WHERE r.itinerary IS NULL")
    List<Ratings> findAllWhereItineraryIsNull();

}
