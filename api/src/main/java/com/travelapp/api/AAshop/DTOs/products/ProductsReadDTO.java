package com.travelapp.api.AAshop.DTOs.products;

import com.travelapp.api.AAshop.DTOs.productreviews.ProductReviewsReadDTO;
import com.travelapp.api.AAshop.DTOs.productreviews.other.ProductReviewsOtherReadDTO;

import java.util.List;

public class ProductsReadDTO {
    private Long productId;
    private String name;
    private String type;
    private Double price;
    private Double rating;
    private Long stock;
    private Long purchaseCount;

    private List<ProductReviewsOtherReadDTO> reviews;

    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getStock() {
        return stock;
    }
    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getPurchaseCount() {
        return purchaseCount;
    }
    public void setPurchaseCount(Long purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public List<ProductReviewsOtherReadDTO> getReviews() {
        return reviews;
    }
    public void setReviews(List<ProductReviewsOtherReadDTO> reviews) {
        this.reviews = reviews;
    }
}
