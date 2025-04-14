package com.travelapp.api.shop.DTOs.productreviews;

import com.travelapp.api.shop.DTOs.products.other.ProductsOtherReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

public class ProductReviewsReadDTO {
    private Long reviewId;
    private UsersOtherReadDTO createdBy;
    private ProductsOtherReadDTO product;
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

    public ProductsOtherReadDTO getProduct() {
        return product;
    }
    public void setProduct(ProductsOtherReadDTO product) {
        this.product = product;
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
