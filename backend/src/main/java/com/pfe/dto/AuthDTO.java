package com.pfe.dto;

import lombok.*;

public class AuthDTO {

    // Login Request
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequest {
        private String email;
        private String password;
    }

    // Register Request
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterRequest {
        private String email;
        private String password;
        private String nom;
        private String prenom;
        private String role;
    }

    // Auth Response
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AuthResponse {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String email;
        private String nom;
        private String prenom;
        private String role;
    }
}
