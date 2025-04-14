package com.travelapp.api.AAshop.repositories;

import com.travelapp.api.AAshop.entities.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser_UserUid(String userUid);
}
