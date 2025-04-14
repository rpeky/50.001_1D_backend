package com.travelapp.api.shop.DTOs.purchasehistory;

import com.travelapp.api.shop.DTOs.products.other.ProductsOtherReadDTO;
import java.time.LocalDateTime;

public class PurchaseHistoryReadListDTO {
    private Long purchaseHistoryId;
    private ProductsOtherReadDTO product;
    private Long quantity;
    private Double totalPrice;
    private LocalDateTime purchasedAt;

    public Long getPurchaseHistoryId() {
        return purchaseHistoryId;
    }
    public void setPurchaseHistoryId(Long purchaseHistoryId) {
        this.purchaseHistoryId = purchaseHistoryId;
    }

    public ProductsOtherReadDTO getProduct() {
        return product;
    }
    public void setProduct(ProductsOtherReadDTO product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getPurchasedAt() {
        return purchasedAt;
    }
    public void setPurchasedAt(LocalDateTime purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}

