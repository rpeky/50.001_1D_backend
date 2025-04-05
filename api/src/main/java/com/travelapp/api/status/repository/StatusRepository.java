package com.travelapp.api.status.repository;

import com.travelapp.api.status.entity.Status;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
