package com.travelapp.api.users.usersettings.DTO;

import com.travelapp.api.users.DTO.other.UsersOtherUpdateDTO;

import org.openapitools.jackson.nullable.JsonNullable;

public class UserSettingsUpdateDTO {
    private JsonNullable<UsersOtherUpdateDTO> user = JsonNullable.undefined();
    private JsonNullable<Boolean> pushNotificationsEnabled = JsonNullable.undefined();
    private JsonNullable<Boolean> emailNotificationsEnabled = JsonNullable.undefined();
    private JsonNullable<Boolean> tripUpdatesEnabled = JsonNullable.undefined();
    private JsonNullable<Boolean> socialUpdatesEnabled = JsonNullable.undefined();
    private JsonNullable<Boolean> marketingNotificationsEnabled = JsonNullable.undefined();
    private JsonNullable<Boolean> biometricAuthEnabled = JsonNullable.undefined();
    private JsonNullable<String> language = JsonNullable.undefined();
    private JsonNullable<String> theme = JsonNullable.undefined();
    private JsonNullable<String> currency = JsonNullable.undefined();

    // getters and setters
    public JsonNullable<UsersOtherUpdateDTO> getUser() {
        return user;
    }
    public void setUser(JsonNullable<UsersOtherUpdateDTO> user) {
        this.user = user;
    }

    public JsonNullable<Boolean> getPushNotificationsEnabled() {
        return pushNotificationsEnabled;
    }
    public void setPushNotificationsEnabled(JsonNullable<Boolean> pushNotificationsEnabled) {
        this.pushNotificationsEnabled = pushNotificationsEnabled;
    }

    public JsonNullable<Boolean> getEmailNotificationsEnabled() {
        return emailNotificationsEnabled;
    }
    public void setEmailNotificationsEnabled(JsonNullable<Boolean> emailNotificationsEnabled) {
        this.emailNotificationsEnabled = emailNotificationsEnabled;
    }

    public JsonNullable<Boolean> getTripUpdatesEnabled() {
        return tripUpdatesEnabled;
    }
    public void setTripUpdatesEnabled(JsonNullable<Boolean> tripUpdatesEnabled) {
        this.tripUpdatesEnabled = tripUpdatesEnabled;
    }

    public JsonNullable<Boolean> getSocialUpdatesEnabled() {
        return socialUpdatesEnabled;
    }
    public void setSocialUpdatesEnabled(JsonNullable<Boolean> socialUpdatesEnabled) {
        this.socialUpdatesEnabled = socialUpdatesEnabled;
    }

    public JsonNullable<Boolean> getMarketingNotificationsEnabled() {
        return marketingNotificationsEnabled;
    }
    public void setMarketingNotificationsEnabled(JsonNullable<Boolean> marketingNotificationsEnabled) {
        this.marketingNotificationsEnabled = marketingNotificationsEnabled;
    }

    public JsonNullable<Boolean> getBiometricAuthEnabled() {
        return biometricAuthEnabled;
    }
    public void setBiometricAuthEnabled(JsonNullable<Boolean> biometricAuthEnabled) {
        this.biometricAuthEnabled = biometricAuthEnabled;
    }

    public JsonNullable<String> getLanguage() {
        return language;
    }
    public void setLanguage(JsonNullable<String> language) {
        this.language = language;
    }

    public JsonNullable<String> getTheme() {
        return theme;
    }
    public void setTheme(JsonNullable<String> theme) {
        this.theme = theme;
    }

    public JsonNullable<String> getCurrency() {
        return currency;
    }
    public void setCurrency(JsonNullable<String> currency) {
        this.currency = currency;
    }
}

