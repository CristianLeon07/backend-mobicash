package com.mobicash.mobicashbackend.infrastructure.repository;



import com.mobicash.mobicashbackend.domain.model.Account;
import com.mobicash.mobicashbackend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByUser(User user);
}