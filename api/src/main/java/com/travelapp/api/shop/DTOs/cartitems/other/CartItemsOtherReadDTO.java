package com.travelapp.api.shop.DTOs.cartitems.other;

import com.travelapp.api.shop.DTOs.products.other.ProductsOtherReadDTO;

public class CartItemsOtherReadDTO {
    private Long cartItemId;
    private ProductsOtherReadDTO product;
    private Long quantity;

    public Long getCartItemId() {
        return cartItemId;
    }
    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
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
}
