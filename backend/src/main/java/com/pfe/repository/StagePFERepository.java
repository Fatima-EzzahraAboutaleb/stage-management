package com.pfe.repository;

import com.pfe.entity.StagePFE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StagePFERepository extends JpaRepository<StagePFE, Long> {
    List<StagePFE> findByStatut(StagePFE.StatutStage statut);
    List<StagePFE> findByEntrepriseId(Long entrepriseId);
    List<StagePFE> findByEncadrantAcademiqueId(Long encadrantId);
    
    @Query("SELECT s FROM StagePFE s WHERE s.sujet LIKE %:search% OR s.description LIKE %:search%")
    List<StagePFE> searchStages(String search);
}
