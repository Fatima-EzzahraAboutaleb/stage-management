package com.pfe.service;

import com.pfe.dto.GenericDTOs;
import com.pfe.entity.StagePFE;
import com.pfe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StagePFERepository stageRepository;
    private final EtudiantRepository etudiantRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EncadrantAcademiqueRepository encadrantRepository;

    /**
     * Récupère les statistiques du dashboard
     */
    public GenericDTOs.DashboardStatsDTO getDashboardStats() {
        long totalStages = stageRepository.count();
        long totalEtudiants = etudiantRepository.count();
        long totalEntreprises = entrepriseRepository.count();
        long totalEncadrants = encadrantRepository.count();
        long stagesEnCours = stageRepository.findByStatut(StagePFE.StatutStage.EN_COURS).size();
        long stagesTermines = stageRepository.findByStatut(StagePFE.StatutStage.TERMINE).size();

        return GenericDTOs.DashboardStatsDTO.builder()
                .totalStages(totalStages)
                .totalEtudiants(totalEtudiants)
                .totalEntreprises(totalEntreprises)
                .totalEncadrants(totalEncadrants)
                .stagesEnCours(stagesEnCours)
                .stagesTermines(stagesTermines)
                .build();
    }
}
