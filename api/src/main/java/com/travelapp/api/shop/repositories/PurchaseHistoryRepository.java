package com.travelapp.api.shop.repositories;

import com.travelapp.api.shop.entities.PurchaseHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    List<PurchaseHistory> findByUser_UserUid(String userUid);
}
