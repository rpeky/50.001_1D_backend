package com.travelapp.api.AAshop.repositories;

import com.travelapp.api.AAshop.entities.PurchaseHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
    List<PurchaseHistory> findByUser_UserUid(String userUid);
}
