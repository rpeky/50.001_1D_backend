package com.travelapp.api.shop.DTOs.cartitems;

import com.travelapp.api.shop.DTOs.cart.CartOtherCreateDTO;
import com.travelapp.api.shop.DTOs.products.other.ProductsOtherCreateDTO;

public class CartItemsCreateDTO {

    private CartOtherCreateDTO cart;
    private ProductsOtherCreateDTO product;
    private Long quantity;

    public CartOtherCreateDTO getCart() {
        return cart;
    }
    public void setCart(CartOtherCreateDTO cart) {
        this.cart = cart;
    }

    public ProductsOtherCreateDTO getProduct() {
        return product;
    }
    public void setProduct(ProductsOtherCreateDTO product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
