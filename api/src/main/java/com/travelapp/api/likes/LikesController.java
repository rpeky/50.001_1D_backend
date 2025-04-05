package com.travelapp.api.likes;

import com.travelapp.api.activities.Activities;
import com.travelapp.api.itineraries.Itineraries;
import com.travelapp.api.users.Users;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/likes") // base path for all likes endpoints
public class LikesController {

    private final LikesRepository likesRepository;

    @Autowired
    public LikesController(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    // READ ALL
    @GetMapping
    public List<Likes> getAllBookmarks() {
        // returns a JSON array of all Likes
        return likesRepository.findAll();
    }

    // READ ONE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Likes> getBookmarkById(@PathVariable Long id) {
        return likesRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Likes> createLikes(@RequestBody Likes likes) {
        Likes created = likesRepository.save(likes);
        return ResponseEntity.status(201).body(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Likes> updateLikes(
            @PathVariable Long id,
            @RequestBody Likes likesDetails) {

        return likesRepository.findById(id)
            .map(existing -> {
                // update only the fields you allow:
                existing.setUser(likesDetails.getUser());
                existing.setActivity(likesDetails.getActivity());
                existing.setItinerary(likesDetails.getItinerary());
                // no need to set createdAt/modifiedAt because they are auto-managed
                // but you could set them if you had a specific use case
                Likes updated = likesRepository.save(existing);
                return ResponseEntity.ok(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLikes(@PathVariable Long id) {
        return likesRepository.findById(id)
            .map(existing -> {
                likesRepository.delete(existing);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // GET all likess for a specific user
    @GetMapping("/user/{userId}")
    public List<Likes> getLikesByUser(@PathVariable Long userId){
        return likesRepository.findByUserUserId(userId);
    }

}
