package com.travelapp.api.bookmarks.repository;

import com.travelapp.api.bookmarks.entity.Bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarksRepository extends JpaRepository<Bookmarks, Long> {
    // return a list of bookmarks that user's ID == userId has bookmarked
    List<Bookmarks> findDistinctByCreatedByUserId(Long userId);
    // return a long of users who bookmarked an activity with activity id == activityId
    long countByActivity_ActivityId(Long activityId);


}
