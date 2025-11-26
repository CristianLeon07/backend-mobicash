package com.mobicash.mobicashbackend.infrastructure.repository;



import com.mobicash.mobicashbackend.domain.model.Transaction;
import com.mobicash.mobicashbackend.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByToAccountOrFromAccount(Account to, Account from);
}
