package com.pfe.dto;

import lombok.*;

public class GenericDTOs {

    // Filiere DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FiliereDTO {
        private Long id;
        private String intitule;
        private Long responsableId;
        private String responsableNom;
    }

    // Entreprise DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EntrepriseDTO {
        private Long id;
        private String nom;
        private String adresse;
        private String telephone;
        private String email;
        private String ville;
        private String pays;
        private String responsableEmail;
        private String encadrantEntrepriseEmail;
        private String encadrantEntrepriseTelephone;
    }

    // Encadrant DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class EncadrantDTO {
        private Long id;
        private String nom;
        private String departement;
        private String etablissement;
        private String email;
    }

    // Responsable Filiere DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponsableFiliereDTO {
        private Long id;
        private String nom;
        private String prenom;
        private String grade;
        private String email;
        private String telephone;
    }

    // API Response Wrapper
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;
        private long timestamp;

        public ApiResponse(String message, T data) {
            this.success = true;
            this.message = message;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }

        public ApiResponse(String message, T data, boolean success) {
            this.success = success;
            this.message = message;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }
    }

    // Dashboard Stats DTO
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DashboardStatsDTO {
        private long totalStages;
        private long totalEtudiants;
        private long totalEntreprises;
        private long totalEncadrants;
        private long stagesEnCours;
        private long stagesTermines;
    }
}
