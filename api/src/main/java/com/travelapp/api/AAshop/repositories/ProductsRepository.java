package com.travelapp.api.AAshop.repositories;

import com.travelapp.api.AAshop.entities.Products;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
}
