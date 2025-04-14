package com.travelapp.api.users.usersettings.DTO;

import com.travelapp.api.users.DTO.other.UsersOtherReadDTO;

import java.time.LocalDateTime;

public class UserSettingsReadDTO {

    private Long userSettingId;
    private UsersOtherReadDTO user;

    private Boolean pushNotificationsEnabled;
    private Boolean emailNotificationsEnabled;
    private Boolean tripUpdatesEnabled;
    private Boolean socialUpdatesEnabled;
    private Boolean marketingNotificationsEnabled;
    private Boolean biometricAuthEnabled;

    private String language;
    private String theme;
    private String currency;

    private LocalDateTime modifiedAt;

    public Long getUserSettingId() {
        return userSettingId;
    }
    public void setUserSettingId(Long userSettingId) {
        this.userSettingId = userSettingId;
    }

    public UsersOtherReadDTO getUser() {
        return user;
    }
    public void setUser(UsersOtherReadDTO user) {
        this.user = user;
    }

    public Boolean getPushNotificationsEnabled() {
        return pushNotificationsEnabled;
    }
    public void setPushNotificationsEnabled(Boolean pushNotificationsEnabled) {
        this.pushNotificationsEnabled = pushNotificationsEnabled;
    }

    public Boolean getEmailNotificationsEnabled() {
        return emailNotificationsEnabled;
    }
    public void setEmailNotificationsEnabled(Boolean emailNotificationsEnabled) {
        this.emailNotificationsEnabled = emailNotificationsEnabled;
    }

    public Boolean getTripUpdatesEnabled() {
        return tripUpdatesEnabled;
    }
    public void setTripUpdatesEnabled(Boolean tripUpdatesEnabled) {
        this.tripUpdatesEnabled = tripUpdatesEnabled;
    }

    public Boolean getSocialUpdatesEnabled() {
        return socialUpdatesEnabled;
    }
    public void setSocialUpdatesEnabled(Boolean socialUpdatesEnabled) {
        this.socialUpdatesEnabled = socialUpdatesEnabled;
    }

    public Boolean getMarketingNotificationsEnabled() {
        return marketingNotificationsEnabled;
    }
    public void setMarketingNotificationsEnabled(Boolean marketingNotificationsEnabled) {
        this.marketingNotificationsEnabled = marketingNotificationsEnabled;
    }

    public Boolean getBiometricAuthEnabled() {
        return biometricAuthEnabled;
    }
    public void setBiometricAuthEnabled(Boolean biometricAuthEnabled) {
        this.biometricAuthEnabled = biometricAuthEnabled;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}

