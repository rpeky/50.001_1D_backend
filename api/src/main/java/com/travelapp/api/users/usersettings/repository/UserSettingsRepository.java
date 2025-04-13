package com.travelapp.api.users.usersettings.repository;

import com.travelapp.api.users.usersettings.entity.UserSettings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> {
    Optional<UserSettings> findByUser_UserUid(String userUid);
}
