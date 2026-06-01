package com.pfe.repository;

import com.pfe.entity.EncadrantAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EncadrantAcademiqueRepository extends JpaRepository<EncadrantAcademique, Long> {
    Optional<EncadrantAcademique> findByEmail(String email);
    
    @Query("SELECT e FROM EncadrantAcademique e WHERE e.nom LIKE %:search% OR e.email LIKE %:search%")
    List<EncadrantAcademique> searchEncadrants(String search);
}
