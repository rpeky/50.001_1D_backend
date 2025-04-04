package com.travelapp.api.bookmarks;

class BookmarkNotFoundException extends RuntimeException{
    BookmarkNotFoundException(Long id){
        super("Bookmark " + id + " not found");
    }
}
