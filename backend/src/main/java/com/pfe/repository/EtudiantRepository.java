package com.pfe.repository;

import com.pfe.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByEmail(String email);
    Optional<Etudiant> findByCne(String cne);
    List<Etudiant> findByFiliereId(Long filiereId);
    
    @Query("SELECT e FROM Etudiant e WHERE e.nom LIKE %:search% OR e.prenom LIKE %:search% OR e.email LIKE %:search%")
    List<Etudiant> searchEtudiants(String search);
}
