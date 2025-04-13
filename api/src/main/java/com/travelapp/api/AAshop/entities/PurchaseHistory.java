package com.travelapp.api.AAshop.entities;

import com.travelapp.api.users.entity.Users;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_history_id")
    private Long purchaseHistoryId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", nullable = false)
    private Users user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_prod_id", referencedColumnName = "product_id", nullable = false)
    private Products product;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "purchased_at", nullable = false)
    private LocalDateTime purchasedAt;

    public Long getPurchaseHistoryId() { return purchaseHistoryId; }
    public void setPurchaseHistoryId(Long purchaseHistoryId) { this.purchaseHistoryId = purchaseHistoryId; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }

    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getPurchasedAt() { return purchasedAt; }
    public void setPurchasedAt(LocalDateTime purchasedAt) { this.purchasedAt = purchasedAt; }
}

