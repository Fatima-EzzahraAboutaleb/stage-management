package com.pfe.repository;

import com.pfe.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Optional<Entreprise> findByNom(String nom);
    Optional<Entreprise> findByEmail(String email);
    
    @Query("SELECT e FROM Entreprise e WHERE e.nom LIKE %:search% OR e.ville LIKE %:search% OR e.email LIKE %:search%")
    List<Entreprise> searchEntreprises(String search);
}
