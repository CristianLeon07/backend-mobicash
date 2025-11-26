package com.mobicash.mobicashbackend.api;


import com.mobicash.mobicashbackend.aplication.dto.UserCreateRequest;
import com.mobicash.mobicashbackend.aplication.service.UserService;
import com.mobicash.mobicashbackend.domain.model.User;
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

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable("userId") String userId) {
        return userService.getById(userId);
    }
}
