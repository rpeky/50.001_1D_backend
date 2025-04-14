package com.travelapp.api.globalnonsense.mappers.mymappers;

import com.travelapp.api.users.usersettings.entity.UserSettings;
import com.travelapp.api.users.usersettings.DTO.UserSettingsUpdateDTO;

public class MyUserSettingsUpdateMapper {

    public static void applyUpdates(UserSettingsUpdateDTO dto, UserSettings settings) {

        if (dto.getPushNotificationsEnabled().isPresent()) {
            settings.setPushNotificationsEnabled(dto.getPushNotificationsEnabled().get());
        }

        if (dto.getEmailNotificationsEnabled().isPresent()) {
            settings.setEmailNotificationsEnabled(dto.getEmailNotificationsEnabled().get());
        }

        if (dto.getTripUpdatesEnabled().isPresent()) {
            settings.setTripUpdatesEnabled(dto.getTripUpdatesEnabled().get());
        }

        if (dto.getSocialUpdatesEnabled().isPresent()) {
            settings.setSocialUpdatesEnabled(dto.getSocialUpdatesEnabled().get());
        }

        if (dto.getMarketingNotificationsEnabled().isPresent()) {
            settings.setMarketingNotificationsEnabled(dto.getMarketingNotificationsEnabled().get());
        }

        if (dto.getBiometricAuthEnabled().isPresent()) {
            settings.setBiometricAuthEnabled(dto.getBiometricAuthEnabled().get());
        }

        if (dto.getLanguage().isPresent()) {
            settings.setLanguage(dto.getLanguage().get());
        }

        if (dto.getTheme().isPresent()) {
            settings.setTheme(dto.getTheme().get());
        }

        if (dto.getCurrency().isPresent()) {
            settings.setCurrency(dto.getCurrency().get());
        }
    }
}

