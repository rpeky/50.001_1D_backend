package com.travelapp.api.itinerarydayactivity.itinerarydays.repository;

import com.travelapp.api.itinerarydayactivity.itinerarydays.entity.ItineraryDay;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryDaysRepository extends JpaRepository<ItineraryDay, Long> {
}
