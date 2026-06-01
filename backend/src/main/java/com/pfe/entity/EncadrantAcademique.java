package com.pfe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "encadrants_academiques")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EncadrantAcademique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String departement;

    @Column(nullable = false)
    private String etablissement;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "encadrantAcademique", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StagePFE> stages;
}
