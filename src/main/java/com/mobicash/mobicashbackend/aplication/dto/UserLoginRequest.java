package com.mobicash.mobicashbackend.aplication.dto;


import jakarta.validation.constraints.NotBlank;

public class UserLoginRequest {

    @NotBlank
    private String documentUser;

    @NotBlank
    private String pin;

    public String getDocumentUser() {
        return documentUser;
    }

    public void setDocumentUser(String documentUser) {
        this.documentUser = documentUser;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}

