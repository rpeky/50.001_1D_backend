package com.travelapp.api.comments.controller;

import com.travelapp.api.comments.DTO.CommentsCreateDTO;
import com.travelapp.api.comments.DTO.CommentsReadDTO;
import com.travelapp.api.comments.DTO.CommentsUpdateDTO;
import com.travelapp.api.comments.service.CommentsService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    // CREATE a new comment
    @PostMapping
    public ResponseEntity<CommentsReadDTO> createComment(@RequestBody CommentsCreateDTO createDTO) {
        CommentsReadDTO created = commentsService.createComment(createDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // GET single comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<CommentsReadDTO> getCommentById(@PathVariable Long id) {
        CommentsReadDTO found = commentsService.getComment(id);
        return ResponseEntity.ok(found);
    }

    // GET all comments (DTO based)
    @GetMapping
    public ResponseEntity<List<CommentsReadDTO>> getAllComments() {
        List<CommentsReadDTO> allComments = commentsService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    // GET comments for a user by numeric ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentsReadDTO>> getCommentsByUser(@PathVariable Long userId) {
        List<CommentsReadDTO> list = commentsService.getCommentsByUser(userId);
        return ResponseEntity.ok(list);
    }

    // NEW: GET comments for a user by UID (UUID lookup)
    @GetMapping("/user/uuid/{userUid}")
    public ResponseEntity<List<CommentsReadDTO>> getCommentsByUserUuid(@PathVariable String userUid) {
        List<CommentsReadDTO> list = commentsService.getCommentsByUserUuid(userUid);
        return ResponseEntity.ok(list);
    }

    // GET comments for a specific activity
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<CommentsReadDTO>> getCommentsByActivity(@PathVariable Long activityId) {
        List<CommentsReadDTO> list = commentsService.getCommentsByActivity(activityId);
        return ResponseEntity.ok(list);
    }

    // GET comments for a specific itinerary
    @GetMapping("/itinerary/{itineraryId}")
    public ResponseEntity<List<CommentsReadDTO>> getCommentsByItinerary(@PathVariable Long itineraryId) {
        List<CommentsReadDTO> list = commentsService.getCommentsByItinerary(itineraryId);
        return ResponseEntity.ok(list);
    }

    // GET the count of comments for a specific activity
    @GetMapping("/activity/{activityId}/count")
    public ResponseEntity<Long> countCommentsByActivity(@PathVariable Long activityId) {
        long count = commentsService.countCommentsByActivity(activityId);
        return ResponseEntity.ok(count);
    }

    // UPDATE a comment
    @PutMapping
    public ResponseEntity<CommentsReadDTO> updateComment(@RequestBody CommentsUpdateDTO updateDTO) {
        CommentsReadDTO updated = commentsService.updateComment(updateDTO);
        return ResponseEntity.ok(updated);
    }

    // DELETE a comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentsService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}

