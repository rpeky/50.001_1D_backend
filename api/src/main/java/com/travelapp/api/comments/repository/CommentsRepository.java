package com.travelapp.api.comments.repository;

import com.travelapp.api.comments.entity.Comments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
