package com.travelapp.api.shop.DTOs.cartitems;

import com.travelapp.api.shop.DTOs.cart.CartOtherReadDTO;
import com.travelapp.api.shop.DTOs.products.other.ProductsOtherReadDTO;

public class CartItemsReadDTO {

    private Long cartItemId;
    private CartOtherReadDTO cart;
    private ProductsOtherReadDTO product;
    private Long quantity;
    private Double totalPrice;

    public Long getCartItemId() {
        return cartItemId;
    }
    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public CartOtherReadDTO getCart() {
        return cart;
    }
    public void setCart(CartOtherReadDTO cart) {
        this.cart = cart;
    }

    public ProductsOtherReadDTO getProduct() {
        return product;
    }
    public void setProduct(ProductsOtherReadDTO product) {
        this.product = product;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
