package com.mobicash.mobicashbackend.api;


import com.mobicash.mobicashbackend.aplication.dto.UserCreateRequest;
import com.mobicash.mobicashbackend.aplication.dto.UserLoginRequest;
import com.mobicash.mobicashbackend.aplication.dto.UserLoginResponse;
import com.mobicash.mobicashbackend.aplication.dto.UserUpdateRequest;
import com.mobicash.mobicashbackend.aplication.service.UserService;
import com.mobicash.mobicashbackend.domain.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Registro (público)
    @PostMapping
    public User create(@Valid @RequestBody UserCreateRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest request) {
        try {
            UserLoginResponse response = userService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            // 👇 devolvemos el mensaje en el body
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(ex.getMessage());
        }
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable("userId") String userId) {
        return userService.getById(userId);
    }

    // endpoint para actualizar datos del usuario
    @PutMapping("/{userId}")
    public User update(
            @PathVariable("userId") String userId,
            @RequestBody UserUpdateRequest request
    ) {
        return userService.updateUser(userId, request);
    }
}
