package com.travelapp.api.likes.controller;

import com.travelapp.api.likes.DTO.LikesReadDTO;
import com.travelapp.api.likes.DTO.LikesCreateDTO;
import com.travelapp.api.likes.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    // GET all likes
    @GetMapping
    public ResponseEntity<List<LikesReadDTO>> getAllLikes() {
        List<LikesReadDTO> list = likesService.getAllLikes();
        return ResponseEntity.ok(list);
    }

    // GET one like by ID
    @GetMapping("/{id}")
    public ResponseEntity<LikesReadDTO> getLikeById(@PathVariable Long id) {
        LikesReadDTO like = likesService.getLike(id);
        return ResponseEntity.ok(like);
    }

    // CREATE a like
    @PostMapping
    public ResponseEntity<LikesReadDTO> createLike(@RequestBody LikesCreateDTO createDTO) {
        LikesReadDTO created = likesService.createLike(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

  
    // DELETE a like
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long id) {
        likesService.deleteLike(id);
        return ResponseEntity.noContent().build();
    }

    // GET likes for a specific user (by numeric ID)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikesReadDTO>> getLikesByUser(@PathVariable Long userId) {
        List<LikesReadDTO> list = likesService.getLikesByUser(userId);
        return ResponseEntity.ok(list);
    }

    // NEW: GET likes for a specific user by UID (UUID lookup)
    @GetMapping("/user/uuid/{userUid}")
    public ResponseEntity<List<LikesReadDTO>> getLikesByUserUuid(@PathVariable String userUid) {
        List<LikesReadDTO> list = likesService.getLikesByUserUuid(userUid);
        return ResponseEntity.ok(list);
    }

    // GET likes for a specific activity
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<LikesReadDTO>> getLikesByActivity(@PathVariable Long activityId) {
        List<LikesReadDTO> list = likesService.getLikesByActivity(activityId);
        return ResponseEntity.ok(list);
    }
    
    // GET count of likes for a specific activity
    @GetMapping("/activity/{activityId}/count")
    public ResponseEntity<Long> countLikesByActivity(@PathVariable Long activityId) {
        long count = likesService.countLikesByActivity(activityId);
        return ResponseEntity.ok(count);
    }
    
    // Optionally, you could add an endpoint for itinerary count (commented out)
    /*
    @GetMapping("/itinerary/{itineraryId}/count")
    public ResponseEntity<Long> countLikesByItinerary(@PathVariable Long itineraryId) {
        long count = likesService.countLikesByItinerary(itineraryId);
        return ResponseEntity.ok(count);
    }
    */
}

