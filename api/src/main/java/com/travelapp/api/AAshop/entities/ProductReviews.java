package com.travelapp.api.AAshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_reviews")
public class ProductReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "fk_prod_id", referencedColumnName = "product_id", nullable = false)
    private Products product;

    @Column(name = "rating")
    private Long rating;

    @Column(name = "review")
    private String review;

    @Column(name = "verified_purchase")
    private Boolean verifiedPurchase = true;

    public Long getReviewId() { return reviewId; }
    public void setReviewId(Long reviewId) { this.reviewId = reviewId; }

    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }

    public Long getRating() { return rating; }
    public void setRating(Long rating) { this.rating = rating; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public Boolean getVerifiedPurchase() { return verifiedPurchase; }
    public void setVerifiedPurchase(Boolean verifiedPurchase) { this.verifiedPurchase = verifiedPurchase; }
}
