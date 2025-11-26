package com.mobicash.mobicashbackend.infrastructure.repository;


import com.mobicash.mobicashbackend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}

