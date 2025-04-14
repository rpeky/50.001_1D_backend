package com.travelapp.api.AAsecurity.DTO;

public class LoginResponseDTO {

    private String token;

    private UsersLoginConfirmationDTO user;

    public LoginResponseDTO(String token, UsersLoginConfirmationDTO user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UsersLoginConfirmationDTO getUser() {
        return user;
    }
    public void setUser(UsersLoginConfirmationDTO user) {
        this.user = user;
    }
}

