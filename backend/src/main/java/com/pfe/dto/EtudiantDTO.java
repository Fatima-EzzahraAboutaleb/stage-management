package com.pfe.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantDTO {

    private Long id;
    private String email;
    private String cne;
    private String nom;
    private String prenom;
    private String telephone;
    private Long filiereId;
    private String filiereNom;
}
