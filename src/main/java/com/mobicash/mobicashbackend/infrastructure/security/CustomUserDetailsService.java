package com.mobicash.mobicashbackend.infrastructure.security;



import com.mobicash.mobicashbackend.domain.model.User;
import com.mobicash.mobicashbackend.infrastructure.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // username = documento (userId), password = PIN (se compara con pinHash)
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // Este es TU User (entidad JPA)
        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Aquí usamos el User de Spring Security con el NOMBRE COMPLETO DEL PAQUETE
        return org.springframework.security.core.userdetails.User
                .withUsername(userEntity.getUserId())
                .password(userEntity.getPinHash())
                .roles("USER")
                .build();
    }
}
