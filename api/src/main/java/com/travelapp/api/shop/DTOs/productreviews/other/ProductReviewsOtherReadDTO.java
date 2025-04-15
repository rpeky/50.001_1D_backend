package com.travelapp.api.shop.DTOs.productreviews.other;

import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

public class ProductReviewsOtherReadDTO {
    private Long reviewId;
    private UsersOtherReadDTO createdBy;
    private Long rating;
    private String review;
    private boolean verifiedPurchase;

    public Long getReviewId() {
        return reviewId;
    }
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public UsersOtherReadDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersOtherReadDTO createdBy) {
        this.createdBy = createdBy;
    }

    public Long getRating() {
        return rating;
    }
    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }

    public boolean isVerifiedPurchase() {
        return verifiedPurchase;
    }
    public void setVerifiedPurchase(boolean verifiedPurchase) {
        this.verifiedPurchase = verifiedPurchase;
    }
}
