package com.travelapp.api.AAshop.DTOs.cartitems;

import com.travelapp.api.AAshop.DTOs.cart.CartOtherReadDTO;
import com.travelapp.api.AAshop.DTOs.products.other.ProductsOtherReadDTO;

public class CartItemsReadDTO {

    private Long cartItemId;
    private CartOtherReadDTO cart;
    private ProductsOtherReadDTO product;
    private Long quantity;

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

    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
