package com.mobicash.mobicashbackend.api;


import com.mobicash.mobicashbackend.aplication.dto.DepositRequest;
import com.mobicash.mobicashbackend.aplication.dto.TransferRequest;
import com.mobicash.mobicashbackend.aplication.service.AccountService;
import com.mobicash.mobicashbackend.domain.model.Account;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Crear cuenta para un usuario
    @PostMapping("/user/{userId}")
    public Account createForUser(@PathVariable String userId) {
        return accountService.createAccountForUser(userId);
    }

    // Obtener cuentas de un usuario
    @GetMapping("/user/{userId}")
    public List<Account> getByUser(@PathVariable String userId) {
        return accountService.getAccountsByUser(userId);
    }

    // Depositar usando BODY
    @PostMapping("/{accountNumber}/deposit")
    public void deposit(
            @PathVariable String accountNumber,
            @RequestBody DepositRequest request
    ) {
        accountService.deposit(accountNumber, request.getAmount());
    }

    // Transferir usando BODY
    @PostMapping("/transfer")
    public void transfer(
            @RequestBody TransferRequest request
    ) {
        accountService.transfer(
                request.getFromAccount(),
                request.getToAccount(),
                request.getAmount()
        );
    }
}

