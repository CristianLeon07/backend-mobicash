package com.mobicash.mobicashbackend.aplication.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateRequest {

    @NotBlank
    private String userId; // documento

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "\\d{4}", message = "El PIN debe tener exactamente 4 dígitos")
    @NotBlank
    private String pin;

    @NotNull
    private LocalDate birthDate;

    private String photo;
}

