package com.travelapp.api.activities.repository;

import com.travelapp.api.activities.entity.Activities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
    List<Activities> findByCreatedBy_UserUid(String userUid);
    Optional<Activities> findByCreatedBy_UserUidAndActivityId(String userUid, Long activityId);
    void deleteByCreatedBy_UserUidAndActivityId(String userUid, Long activityId);
}
