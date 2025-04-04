package com.travelapp.api.bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarksRepository extends JpaRepository<Bookmarks, Long> {
    // return a list of bookmarks that user's ID == userId has bookmarked
    List<Bookmarks> findByCreatedByUserId(Long userId);

}
