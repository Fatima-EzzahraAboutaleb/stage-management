package com.pfe.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StagePFEDTO {

    private Long id;
    private String sujet;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String description;
    private String objectifs;
    private String solution;
    private String demarcheProjet;
    private String outilsUtilises;
    private String anneeUniversitaire;
    private String statut;
    
    // Relations
    private Long entrepriseId;
    private String entrepriseNom;
    private Long encadrantId;
    private String encadrantNom;
    private List<Long> etudiantIds;
    private List<String> etudiantNoms;
}
