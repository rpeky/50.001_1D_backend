package com.travelapp.api.bookmarks.service;

class BookmarkNotFoundException extends RuntimeException{
    BookmarkNotFoundException(Long id){
        super("Bookmark " + id + " not found");
    }
}
