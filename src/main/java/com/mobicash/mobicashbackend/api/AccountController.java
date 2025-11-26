package com.mobicash.mobicashbackend.api;


import com.mobicash.mobicashbackend.aplication.service.AccountService;
import com.mobicash.mobicashbackend.domain.model.Account;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
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

    @PostMapping("/user/{userId}")
    public Account createForUser(@PathVariable("userId") String userId) {
        return accountService.createAccountForUser(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Account> getByUser(@PathVariable("userId") String userId) {
        return accountService.getAccountsByUser(userId);
    }

    @PostMapping("/{accountNumber}/deposit")
    public void deposit(@PathVariable("accountNumber") String accountNumber,
                        @RequestParam("amount") BigDecimal amount) {
        accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam("fromAccount") String fromAccount,
                         @RequestParam("toAccount") String toAccount,
                         @RequestParam("amount") BigDecimal amount) {
        accountService.transfer(fromAccount, toAccount, amount);
    }
}
