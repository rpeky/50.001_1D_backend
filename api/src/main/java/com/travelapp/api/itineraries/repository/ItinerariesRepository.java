package com.travelapp.api.itineraries.repository;

import com.travelapp.api.itineraries.entity.Itineraries;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItinerariesRepository extends JpaRepository<Itineraries, Long> {
    List<Itineraries> findByCreatedBy_UserUid(String userUid);
}
