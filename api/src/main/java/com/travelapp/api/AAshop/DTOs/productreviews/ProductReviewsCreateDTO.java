package com.travelapp.api.AAshop.DTOs.productreviews;

import com.travelapp.api.AAshop.DTOs.products.other.ProductsOtherCreateDTO;
import com.travelapp.api.users.DTO.other.UsersOtherCreateDTO;

public class ProductReviewsCreateDTO {
    private ProductsOtherCreateDTO product;
    private UsersOtherCreateDTO createdBy;
    private Long rating;
    private String review;

    public ProductsOtherCreateDTO getProduct() {
        return product;
    }
    public void setProduct(ProductsOtherCreateDTO product) {
        this.product = product;
    }

    public UsersOtherCreateDTO getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UsersOtherCreateDTO createdBy) {
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
}
