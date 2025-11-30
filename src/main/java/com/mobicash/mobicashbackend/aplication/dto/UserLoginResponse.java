package com.mobicash.mobicashbackend.aplication.dto;


public class UserLoginResponse {

    private String userId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private String photo;

    public UserLoginResponse() {
    }

    public UserLoginResponse(String userId,
                             String documentNumber,
                             String firstName,
                             String lastName,
                             String email,
                             String birthDate,
                             String photo) {
        this.userId = userId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.photo = photo;
    }

    // getters & setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
