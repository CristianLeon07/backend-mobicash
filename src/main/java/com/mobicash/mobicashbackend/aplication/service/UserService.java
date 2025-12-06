package com.mobicash.mobicashbackend.aplication.service;



import com.mobicash.mobicashbackend.aplication.dto.UserCreateRequest;
import com.mobicash.mobicashbackend.aplication.dto.UserLoginRequest;
import com.mobicash.mobicashbackend.aplication.dto.UserLoginResponse;
import com.mobicash.mobicashbackend.aplication.dto.UserUpdateRequest;
import com.mobicash.mobicashbackend.domain.model.User;
import com.mobicash.mobicashbackend.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserCreateRequest request) {

        if (userRepository.findByDocumentUser(request.getDocumentUser()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUserId(request.getUserId());
        user.setDocumentUser(request.getDocumentUser());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthDate(request.getBirthDate());
        user.setPhoto(request.getPhoto());
        user.setPinHash(passwordEncoder.encode(request.getPin())); // PIN cifrado

        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserLoginResponse login(UserLoginRequest request) {

        System.out.println("Intento de login para userId=" + request.getDocumentUser());

        User user = userRepository.findByDocumentUser(request.getDocumentUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        System.out.println("Usuario encontrado, email=" + user.getEmail());
        System.out.println("PIN enviado=" + request.getPin());
        System.out.println("PIN hash guardado=" + user.getPinHash());

        boolean matches = passwordEncoder.matches(request.getPin(), user.getPinHash());

        if (!matches) {
            throw new RuntimeException("Usuario o PIN incorrecto");
        }

        return new UserLoginResponse(
                user.getUserId(),
                user.getDocumentUser(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate().toString(),
                user.getPhoto()
        );
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getBirthDate() != null) {
            user.setBirthDate(request.getBirthDate());
        }
        if (request.getPhoto() != null) {
            user.setPhoto(request.getPhoto());
        }

        return userRepository.save(user);
    }


}

