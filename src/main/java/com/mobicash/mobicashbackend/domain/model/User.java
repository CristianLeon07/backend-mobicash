package com.mobicash.mobicashbackend.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
public class User {

    // Documento de identidad como PK
    @Id
    @Column(name = "user_id", length = 20)
    @NotBlank
    private String userId;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    private String lastName;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank
    private String email;

    // Guardamos el PIN cifrado, no plano
    @JsonIgnore
    @Column(name = "pin_hash", nullable = false)
    private String pinHash;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    // URL o base64 de la foto
    private String photo;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
