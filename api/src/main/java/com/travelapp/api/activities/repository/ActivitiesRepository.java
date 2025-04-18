package com.travelapp.api.activities.repository;

import com.travelapp.api.activities.entity.Activities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
    List<Activities> findByCreatedBy_UserUid(String userUid);
    boolean existsByActivityId(Long activityId);
}
