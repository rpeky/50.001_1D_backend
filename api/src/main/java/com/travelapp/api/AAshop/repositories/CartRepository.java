package com.travelapp.api.AAshop.repositories;

import com.travelapp.api.AAshop.entities.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
