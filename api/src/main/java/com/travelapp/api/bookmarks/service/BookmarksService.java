package com.travelapp.api.bookmarks.service;

import com.travelapp.api.bookmarks.DTO.BookmarksCreateDTO;
import com.travelapp.api.bookmarks.DTO.BookmarksReadDTO;
//import com.travelapp.api.bookmarks.DTO.BookmarksUpdateDTO;
import java.util.List;

public interface BookmarksService {
    BookmarksReadDTO createBookmark(BookmarksCreateDTO createDTO);
    BookmarksReadDTO getBookmark(Long bookmarkId);
    List<BookmarksReadDTO> getAllBookmarks();
    List<BookmarksReadDTO> getBookmarksByUser(Long userId);
    List<BookmarksReadDTO> getBookmarksByUserUuid(String userUid);
    long countBookmarksByActivity(Long activityId);
    void deleteBookmark(Long bookmarkId);
}

