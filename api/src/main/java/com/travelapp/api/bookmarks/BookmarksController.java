package com.travelapp.api.bookmarks;

import com.travelapp.api.activities.Activities;
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
@RequestMapping("/api/bookmarks") // base path for all bookmark endpoints
public class BookmarksController {

    private final BookmarksRepository bookmarksRepository;

    @Autowired
    public BookmarksController(BookmarksRepository bookmarksRepository) {
        this.bookmarksRepository = bookmarksRepository;
    }

    // READ ALL
    @GetMapping
    public List<Bookmarks> getAllBookmarks() {
        // returns a JSON array of Bookmarks
        return bookmarksRepository.findAll();
    }

    // READ ONE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Bookmarks> getBookmarkById(@PathVariable Long id) {
        return bookmarksRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Bookmarks> createBookmark(@RequestBody Bookmarks bookmark) {
        Bookmarks created = bookmarksRepository.save(bookmark);
        return ResponseEntity.status(201).body(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Bookmarks> updateBookmark(
            @PathVariable Long id,
            @RequestBody Bookmarks bookmarkDetails) {

        return bookmarksRepository.findById(id)
            .map(existing -> {
                // update only the fields you allow:
                existing.setCreatedBy(bookmarkDetails.getCreatedBy());
                existing.setActivity(bookmarkDetails.getActivity());
                // no need to set createdAt/modifiedAt because they are auto-managed
                // but you could set them if you had a specific use case
                Bookmarks updated = bookmarksRepository.save(existing);
                return ResponseEntity.ok(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        return bookmarksRepository.findById(id)
            .map(existing -> {
                bookmarksRepository.delete(existing);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // GET all bookmarks for a specific user
    @GetMapping("/user/{userId}")
    public List<Bookmarks> getBookmarksByUser(@PathVariable Long userId){
        return bookmarksRepository.findByCreatedByUserId(userId);
    }

}
