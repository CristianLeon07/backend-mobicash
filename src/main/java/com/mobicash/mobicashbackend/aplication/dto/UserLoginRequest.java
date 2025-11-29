package com.mobicash.mobicashbackend.aplication.dto;


import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String pin;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}

