package com.travelapp.api.shop.repositories;

import com.travelapp.api.shop.entities.CartItems;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}
