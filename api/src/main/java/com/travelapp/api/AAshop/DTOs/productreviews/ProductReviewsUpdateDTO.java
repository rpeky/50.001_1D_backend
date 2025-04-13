package com.travelapp.api.AAshop.DTOs.productreviews;

import com.travelapp.api.AAshop.DTOs.products.other.ProductsOtherCreateDTO;
import com.travelapp.api.AAshop.DTOs.products.other.ProductsOtherUpdateDTO;
import com.travelapp.api.users.DTO.other.UsersOtherUpdateDTO;

import org.openapitools.jackson.nullable.JsonNullable;

public class ProductReviewsUpdateDTO {
    private Long reviewId;

    private JsonNullable<UsersOtherUpdateDTO> createdBy = JsonNullable.undefined();
    private JsonNullable<ProductsOtherUpdateDTO> product = JsonNullable.undefined();

    private JsonNullable<Long> rating = JsonNullable.undefined();
    private JsonNullable<String> review = JsonNullable.undefined();

    private JsonNullable<Boolean> verifiedPurchase = JsonNullable.undefined();

    public Long getReviewId() {
        return reviewId;
    }
    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public JsonNullable<UsersOtherUpdateDTO> getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(JsonNullable<UsersOtherUpdateDTO> createdBy) {
        this.createdBy = createdBy;
    }

    public JsonNullable<ProductsOtherUpdateDTO> getProduct() {
        return product;
    }
    public void setProduct(JsonNullable<ProductsOtherUpdateDTO> product) {
        this.product = product;
    }

    public JsonNullable<Long> getRating() {
        return rating;
    }
    public void setRating(JsonNullable<Long> rating) {
        this.rating = rating;
    }

    public JsonNullable<String> getReview() {
        return review;
    }
    public void setReview(JsonNullable<String> review) {
        this.review = review;
    }

    public JsonNullable<Boolean> getVerifiedPurchase() {
        return verifiedPurchase;
    }
    public void setVerifiedPurchase(JsonNullable<Boolean> verifiedPurchase) {
        this.verifiedPurchase = verifiedPurchase;
    }
}
