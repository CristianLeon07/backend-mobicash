package com.mobicash.mobicashbackend.domain.model;



import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "from_account_number")
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_number")
    private Account toAccount; // puede ser null en depósitos

    public enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }
}
