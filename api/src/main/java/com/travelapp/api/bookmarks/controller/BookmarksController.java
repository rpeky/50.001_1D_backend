package com.travelapp.api.bookmarks.controller;

import com.travelapp.api.bookmarks.DTO.BookmarksCreateDTO;
import com.travelapp.api.bookmarks.DTO.BookmarksReadDTO;
//import com.travelapp.api.bookmarks.DTO.BookmarksUpdateDTO;
import com.travelapp.api.bookmarks.service.BookmarksService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarksController {

    @Autowired
    private BookmarksService bookmarksService;

    // CREATE
    @PostMapping
    public ResponseEntity<BookmarksReadDTO> createBookmark(@RequestBody BookmarksCreateDTO createDTO) {
        BookmarksReadDTO created = bookmarksService.createBookmark(createDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public List<BookmarksReadDTO> getAllBookmarks() {
        // returns a JSON array of BookmarksReadDTO
        return bookmarksService.getAllBookmarks();
    }

    // READ ONE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BookmarksReadDTO> getBookmarkById(@PathVariable Long id) {
        BookmarksReadDTO found = bookmarksService.getBookmark(id);
        return ResponseEntity.ok(found);
    }

    // READ all bookmarks for a specific user
    @GetMapping("/user/{userId}")
    public List<BookmarksReadDTO> getBookmarksByUser(@PathVariable Long userId) {
        return bookmarksService.getBookmarksByUser(userId);
    }

    // GET bookmarks based on user UID instead of numerical userId
    @GetMapping("/user/uuid/{userUid}")
    public ResponseEntity<List<BookmarksReadDTO>> getBookmarksByUserUuid(@PathVariable String userUid) {
        List<BookmarksReadDTO> bookmarks = bookmarksService.getBookmarksByUserUuid(userUid);
        return ResponseEntity.ok(bookmarks);
    }

    @GetMapping("/activity/{activityId}/count")
    public ResponseEntity<Long> countBookmarksByActivity(@PathVariable Long activityId) {
        long count = bookmarksService.countBookmarksByActivity(activityId);
        return ResponseEntity.ok(count);
    }


    /*
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BookmarksReadDTO> updateBookmark(
        @PathVariable Long id,
        @RequestBody BookmarksUpdateDTO updateDTO
    ) {
        // pass the ID + updateDTO into the service
        BookmarksReadDTO updated = bookmarksService.updateBookmark(id, updateDTO);
        return ResponseEntity.ok(updated);
    }
    */

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long id) {
        bookmarksService.deleteBookmark(id);
        return ResponseEntity.noContent().build();
    }
}
