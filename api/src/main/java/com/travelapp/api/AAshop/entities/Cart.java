package com.travelapp.api.AAshop.entities;

import com.travelapp.api.users.entity.Users;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", nullable = false)
    private Users user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItems> cartItems;

    public Cart() {
    }

    public Cart(Users user) {
        this.user = user;
    }

    public Long getCartId() { return cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public List<CartItems> getCartItems() { return cartItems; }
    public void setCartItems(List<CartItems> cartItems) { this.cartItems = cartItems; }
}
