package com.travelapp.api.AAshop.repositories;

import com.travelapp.api.AAshop.entities.CartItems;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}
