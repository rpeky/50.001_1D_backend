package com.travelapp.api.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    // return a list of likes that user's ID == userId has liked
    List<Likes> findByUserUserId(Long userId);

}
