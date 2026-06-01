package com.pfe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "responsables_filieres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponsableFiliere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String grade;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @OneToOne(mappedBy = "responsable")
    private Filiere filiere;
}
