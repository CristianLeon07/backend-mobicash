package com.mobicash.mobicashbackend.aplication.service;


import com.mobicash.mobicashbackend.domain.model.Account;
import com.mobicash.mobicashbackend.domain.model.Transaction;
import com.mobicash.mobicashbackend.domain.model.User;
import com.mobicash.mobicashbackend.infrastructure.repository.AccountRepository;
import com.mobicash.mobicashbackend.infrastructure.repository.TransactionRepository;
import com.mobicash.mobicashbackend.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;



@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          UserRepository userRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Account createAccountForUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Account account = new Account();
        account.setUser(user);
        account.setAccountNumber(generateAccountNumber());
        account.setCvc(generateCvc());
        account.setBalance(BigDecimal.ZERO);

        return accountRepository.save(account);
    }

    public List<Account> getAccountsByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return accountRepository.findByUser(user);
    }

    // DEPÓSITO

    @Transactional
    public void deposit(String accountNumber, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El monto debe ser mayor que cero");
        }

        // Ahora buscamos por accountNumber, no por id
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction tx = new Transaction();
        tx.setToAccount(account);
        tx.setAmount(amount);
        tx.setType(Transaction.TransactionType.DEPOSIT);
        tx.setDescription("Depósito");
        transactionRepository.save(tx);
    }

    // TRANSFERENCIA

    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El monto debe ser mayor que cero");
        }

        // 👇 Igual, buscamos por accountNumber
        Account from = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        Account to = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);

        Transaction tx = new Transaction();
        tx.setFromAccount(from);
        tx.setToAccount(to);
        tx.setAmount(amount);
        tx.setType(Transaction.TransactionType.TRANSFER);
        tx.setDescription("Transferencia");
        transactionRepository.save(tx);
    }

    // UTILIDADES

    private String generateAccountNumber() {
        int num = new Random().nextInt(900000) + 100000; // 100000–999999
        return "MC-" + num;
    }

    private String generateCvc() {
        int num = new Random().nextInt(900) + 100; // 100–999
        return String.valueOf(num);
    }
}
