package com.travelapp.api.users.userfollows.repository;

import com.travelapp.api.users.entity.Users;
import com.travelapp.api.users.userfollows.entity.UserFollows;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowsRepository extends JpaRepository<UserFollows, Long> {
    List<UserFollows> findByFollower_UserId(Long userId);
    List<UserFollows> findByFollowing_UserId(Long userId);
}
