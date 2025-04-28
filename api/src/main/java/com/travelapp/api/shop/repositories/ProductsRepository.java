package com.travelapp.api.shop.repositories;

import com.travelapp.api.shop.entities.Products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
