package com.pfe.service;

import com.pfe.dto.AuthDTO;
import com.pfe.entity.User;
import com.pfe.repository.UserRepository;
import com.pfe.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    /**
     * Authentifie l'utilisateur et retourne un JWT token
     */
    public AuthDTO.AuthResponse login(AuthDTO.LoginRequest request) {
        // Authentifier l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Générer le token JWT
        String token = tokenProvider.generateToken(authentication);

        // Récupérer les informations de l'utilisateur
        User user = userRepository.findByEmail(request.getEmail()).get();

        return AuthDTO.AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .email(user.getEmail())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .role(user.getRole().toString())
                .build();
    }

    /**
     * Enregistre un nouvel utilisateur
     */
    public AuthDTO.AuthResponse register(AuthDTO.RegisterRequest request) {
        // Vérifier si l'email existe déjà
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Créer un nouvel utilisateur
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .role(User.Role.valueOf(request.getRole()))
                .active(true)
                .build();

        user = userRepository.save(user);

        // Générer le token JWT
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null,
                new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + user.getRole().toString())
        );

        String token = tokenProvider.generateToken(authentication);

        return AuthDTO.AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .email(user.getEmail())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .role(user.getRole().toString())
                .build();
    }

    /**
     * Récupère l'utilisateur actuel depuis le contexte de sécurité
     */
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Current user not found"));
    }
}
