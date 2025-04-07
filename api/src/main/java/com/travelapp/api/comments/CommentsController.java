package com.travelapp.api.comments;

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
@RequestMapping("/api/comments") // base path for all comments endpoints
public class CommentsController {

    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsController(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    // READ ALL
    @GetMapping
    public List<Comments> getAllComments() {
        // returns a JSON array of all comments
        return commentsRepository.findAll();
    }

    // READ ONE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Comments> getCommentsById(@PathVariable Long id) {
        return commentsRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Comments> createComment(@RequestBody Comments comment) {
        Comments created = commentsRepository.save(comment);
        return ResponseEntity.status(201).body(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Comments> updateComment(
            @PathVariable Long id,
            @RequestBody Comments commentDetails) {

        return commentsRepository.findById(id)
            .map(existing -> {
                // update only the fields you allow:
                existing.setCreatedBy(commentDetails.getCreatedBy());
                existing.setActivity(commentDetails.getActivity());
                existing.setItineraries(commentDetails.getItineraries());
                existing.setComment(commentDetails.getComment());
                // no need to set createdAt/modifiedAt because they are auto-managed
                // but you could set them if you had a specific use case
                Comments updated = commentsRepository.save(existing);
                return ResponseEntity.ok(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        return commentsRepository.findById(id)
            .map(existing -> {
                commentsRepository.delete(existing);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // GET all comments for a specific user
    @GetMapping("/user/{userId}")
    public List<Comments> getCommentsByUser(@PathVariable Long userId){
        return commentsRepository.findByCreatedByUserId(userId);
    }

   // GET all comments for a specific activity
    @GetMapping("/activity/{activityId}")
    public List<Comments> getCommentsByActivity(@PathVariable Long activityId){
        return commentsRepository.findByActivityActivityId(activityId);
    }

   // GET all comments for a specific itinerary
    @GetMapping("/itinerary/{itineraryId}")
    public List<Comments> getCommentsByItinerary(@PathVariable Long itineraryId){
        return commentsRepository.findByItineraryItineraryId(itineraryId);
    }

}
