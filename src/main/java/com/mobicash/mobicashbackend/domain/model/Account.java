package com.mobicash.mobicashbackend.domain.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "accounts")
public class Account {

    // PK: número de cuenta
    @Id
    @Column(name = "account_number", length = 20)
    private String accountNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;   // relación con User

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(length = 3, nullable = false)
    private String cvc;  // 3 dígitos

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    @Column(name = "name_user")
    private String nameUser; // nombre completo cacheado (opcional)

    public enum AccountStatus {
        ACTIVE,
        BLOCKED
    }
}
