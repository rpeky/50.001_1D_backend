package com.travelapp.api.bookmarks.repository;

import com.travelapp.api.bookmarks.entity.Bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarksRepository extends JpaRepository<Bookmarks, Long> {

}
