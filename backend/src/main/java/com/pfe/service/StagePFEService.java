package com.pfe.service;

import com.pfe.dto.StagePFEDTO;
import com.pfe.entity.*;
import com.pfe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StagePFEService {

    private final StagePFERepository stageRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EncadrantAcademiqueRepository encadrantRepository;
    private final EtudiantRepository etudiantRepository;

    /**
     * Récupère tous les stages
     */
    public List<StagePFEDTO> getAllStages() {
        return stageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un stage par son ID
     */
    public StagePFEDTO getStagePFEById(Long id) {
        StagePFE stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found"));
        return convertToDTO(stage);
    }

    /**
     * Crée un nouveau stage
     */
    public StagePFEDTO createStagePFE(StagePFEDTO dto) {
        Entreprise entreprise = entrepriseRepository.findById(dto.getEntrepriseId())
                .orElseThrow(() -> new RuntimeException("Entreprise not found"));

        EncadrantAcademique encadrant = encadrantRepository.findById(dto.getEncadrantId())
                .orElseThrow(() -> new RuntimeException("Encadrant not found"));

        StagePFE stage = StagePFE.builder()
                .sujet(dto.getSujet())
                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .description(dto.getDescription())
                .objectifs(dto.getObjectifs())
                .solution(dto.getSolution())
                .demarcheProjet(dto.getDemarcheProjet())
                .outilsUtilises(dto.getOutilsUtilises())
                .anneeUniversitaire(dto.getAnneeUniversitaire())
                .statut(StagePFE.StatutStage.valueOf(dto.getStatut()))
                .entreprise(entreprise)
                .encadrantAcademique(encadrant)
                .build();

        stage = stageRepository.save(stage);
        return convertToDTO(stage);
    }

    /**
     * Met à jour un stage
     */
    public StagePFEDTO updateStagePFE(Long id, StagePFEDTO dto) {
        StagePFE stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        stage.setSujet(dto.getSujet());
        stage.setDateDebut(dto.getDateDebut());
        stage.setDateFin(dto.getDateFin());
        stage.setDescription(dto.getDescription());
        stage.setObjectifs(dto.getObjectifs());
        stage.setSolution(dto.getSolution());
        stage.setDemarcheProjet(dto.getDemarcheProjet());
        stage.setOutilsUtilises(dto.getOutilsUtilises());
        stage.setAnneeUniversitaire(dto.getAnneeUniversitaire());
        stage.setStatut(StagePFE.StatutStage.valueOf(dto.getStatut()));

        stage = stageRepository.save(stage);
        return convertToDTO(stage);
    }

    /**
     * Supprime un stage
     */
    public void deleteStagePFE(Long id) {
        stageRepository.deleteById(id);
    }

    /**
     * Récupère tous les stages avec un statut spécifique
     */
    public List<StagePFEDTO> getStagesByStatut(String statut) {
        return stageRepository.findByStatut(StagePFE.StatutStage.valueOf(statut))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Recherche des stages
     */
    public List<StagePFEDTO> searchStages(String keyword) {
        return stageRepository.searchStages(keyword)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Assigne un étudiant à un stage
     */
    public void assignEtudiantToStage(Long stageId, Long etudiantId) {
        StagePFE stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Etudiant not found"));

        if (!stage.getEtudiants().contains(etudiant)) {
            stage.getEtudiants().add(etudiant);
            stageRepository.save(stage);
        }
    }

    /**
     * Retire un étudiant d'un stage
     */
    public void removeEtudiantFromStage(Long stageId, Long etudiantId) {
        StagePFE stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new RuntimeException("Stage not found"));

        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Etudiant not found"));

        stage.getEtudiants().remove(etudiant);
        stageRepository.save(stage);
    }

    /**
     * Convertit une entité en DTO
     */
    private StagePFEDTO convertToDTO(StagePFE stage) {
        List<String> etudiantNoms = stage.getEtudiants().stream()
                .map(e -> e.getNom() + " " + e.getPrenom())
                .collect(Collectors.toList());

        List<Long> etudiantIds = stage.getEtudiants().stream()
                .map(Etudiant::getId)
                .collect(Collectors.toList());

        return StagePFEDTO.builder()
                .id(stage.getId())
                .sujet(stage.getSujet())
                .dateDebut(stage.getDateDebut())
                .dateFin(stage.getDateFin())
                .description(stage.getDescription())
                .objectifs(stage.getObjectifs())
                .solution(stage.getSolution())
                .demarcheProjet(stage.getDemarcheProjet())
                .outilsUtilises(stage.getOutilsUtilises())
                .anneeUniversitaire(stage.getAnneeUniversitaire())
                .statut(stage.getStatut().toString())
                .entrepriseId(stage.getEntreprise().getId())
                .entrepriseNom(stage.getEntreprise().getNom())
                .encadrantId(stage.getEncadrantAcademique().getId())
                .encadrantNom(stage.getEncadrantAcademique().getNom())
                .etudiantIds(etudiantIds)
                .etudiantNoms(etudiantNoms)
                .build();
    }
}
