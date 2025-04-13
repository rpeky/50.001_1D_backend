package com.travelapp.api.AAshop.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "purchase_count")
    private Long purchaseCount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductReviews> reviews;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getStock() { return stock; }
    public void setStock(Long stock) { this.stock = stock; }

    public Long getPurchaseCount() {
        return purchaseCount;
    }
    public void setPurchaseCount(Long purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public List<ProductReviews> getReviews() { return reviews; }
    public void setReviews(List<ProductReviews> reviews) { this.reviews = reviews; }
}
