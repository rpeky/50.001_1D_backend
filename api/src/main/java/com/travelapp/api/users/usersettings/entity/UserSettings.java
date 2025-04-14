package com.travelapp.api.users.usersettings.entity;

import com.travelapp.api.globalnonsense.datedentityandothers.ModifiedAtTracker;
import com.travelapp.api.users.entity.Users;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_settings")
public class UserSettings extends ModifiedAtTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_setting_id")
    private Long userSettingId;

    @OneToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id", nullable = false)
    private Users user;

    @Column(name = "push_notifications_enabled")
    private Boolean pushNotificationsEnabled = true;

    @Column(name = "email_notifications_enabled")
    private Boolean emailNotificationsEnabled = true;

    @Column(name = "trip_updates_enabled")
    private Boolean tripUpdatesEnabled = true;

    @Column(name = "social_updates_enabled")
    private Boolean socialUpdatesEnabled = true;

    @Column(name = "marketing_notifications_enabled")
    private Boolean marketingNotificationsEnabled = false;

    @Column(name = "biometric_auth_enabled")
    private Boolean biometricAuthEnabled = false;

    @Column(name = "language")
    private String language = "en";

    @Column(name = "theme")
    private String theme = "light";

    @Column(name = "currency")
    private String currency = "USD";

    // Constructors
    public UserSettings() {}

    public UserSettings(Users user) {
        this.user = user;
    }

    // Getters & Setters
    public Long getUserSettingId() {
        return userSettingId;
    }
    public void setUserSettingId(Long userSettingId) {
        this.userSettingId = userSettingId;
    }

    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
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
}

