package com.travelapp.api.AAshop.DTOs.cart;

import com.travelapp.api.AAshop.DTOs.cartitems.other.CartItemsOtherReadDTO;
import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.util.List;

public class CartReadDTO {
    private Long cartId;
    private UsersOtherReadDTO user;
    private List<CartItemsOtherReadDTO> cartItems;

    public Long getCartId() {
        return cartId;
    }
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public UsersOtherReadDTO getUser() {
        return user;
    }
    public void setUser(UsersOtherReadDTO user) {
        this.user = user;
    }

    public List<CartItemsOtherReadDTO> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItemsOtherReadDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
