package com.pfe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "stages_pfe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StagePFE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sujet;

    @Column(nullable = false)
    private LocalDate dateDebut;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String objectifs;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String solution;

    @Column(columnDefinition = "TEXT")
    private String demarcheProjet;

    @Column(columnDefinition = "TEXT")
    private String outilsUtilises;

    @Column(nullable = false)
    private String anneeUniversitaire;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutStage statut;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entreprise_id", nullable = false)
    private Entreprise entreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encadrant_id", nullable = false)
    private EncadrantAcademique encadrantAcademique;

    @ManyToMany
    @JoinTable(
            name = "stage_etudiants",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id")
    )
    private List<Etudiant> etudiants;

    // Enum pour les statuts
    public enum StatutStage {
        PROPOSE, EN_COURS, TERMINE, ANNULE
    }
}
