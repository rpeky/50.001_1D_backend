package com.travelapp.api.users.repository;

import com.travelapp.api.users.entity.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserUid(String userUid);
    void deleteByUserUid(String userUid);
    boolean existsByUserUid(String userUid);
}
