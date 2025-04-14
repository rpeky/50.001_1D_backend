package com.travelapp.api.shop.DTOs.cartitems;

public class CartItemsUpdateDTO {
    private Long cartItemId;
    private Long quantity;

    public Long getCartItemId() {
        return cartItemId;
    }
    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
