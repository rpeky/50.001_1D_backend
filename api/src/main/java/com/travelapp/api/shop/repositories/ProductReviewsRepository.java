package com.travelapp.api.shop.repositories;

import com.travelapp.api.shop.entities.ProductReviews;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewsRepository extends JpaRepository<ProductReviews, Long> {
}
