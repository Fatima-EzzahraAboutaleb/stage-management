package com.pfe.controller;

import com.pfe.dto.GenericDTOs;
import com.pfe.dto.StagePFEDTO;
import com.pfe.service.StagePFEService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard/stages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class StagePFEController {

    private final StagePFEService stagePFEService;

    /**
     * GET /dashboard/stages - Récupère tous les stages
     */
    @GetMapping
    public ResponseEntity<GenericDTOs.ApiResponse<List<StagePFEDTO>>> getAllStages() {
        List<StagePFEDTO> stages = stagePFEService.getAllStages();
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Stages retrieved successfully", stages));
    }

    /**
     * GET /dashboard/stages/{id} - Récupère un stage par ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenericDTOs.ApiResponse<StagePFEDTO>> getStagePFEById(@PathVariable Long id) {
        StagePFEDTO stage = stagePFEService.getStagePFEById(id);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Stage retrieved successfully", stage));
    }

    /**
     * POST /dashboard/stages - Crée un nouveau stage
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENCADRANT')")
    public ResponseEntity<GenericDTOs.ApiResponse<StagePFEDTO>> createStagePFE(@RequestBody StagePFEDTO dto) {
        StagePFEDTO stage = stagePFEService.createStagePFE(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericDTOs.ApiResponse<>("Stage created successfully", stage)
        );
    }

    /**
     * PUT /dashboard/stages/{id} - Met à jour un stage
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<StagePFEDTO>> updateStagePFE(
            @PathVariable Long id,
            @RequestBody StagePFEDTO dto) {
        StagePFEDTO stage = stagePFEService.updateStagePFE(id, dto);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Stage updated successfully", stage));
    }

    /**
     * DELETE /dashboard/stages/{id} - Supprime un stage
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<String>> deleteStagePFE(@PathVariable Long id) {
        stagePFEService.deleteStagePFE(id);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Stage deleted successfully", ""));
    }

    /**
     * GET /dashboard/stages/search/{keyword} - Recherche des stages
     */
    @GetMapping("/search/{keyword}")
    public ResponseEntity<GenericDTOs.ApiResponse<List<StagePFEDTO>>> searchStages(@PathVariable String keyword) {
        List<StagePFEDTO> stages = stagePFEService.searchStages(keyword);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Stages found", stages));
    }

    /**
     * GET /dashboard/stages/statut/{statut} - Récupère les stages par statut
     */
    @GetMapping("/statut/{statut}")
    public ResponseEntity<GenericDTOs.ApiResponse<List<StagePFEDTO>>> getStagesByStatut(@PathVariable String statut) {
        List<StagePFEDTO> stages = stagePFEService.getStagesByStatut(statut);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Stages retrieved successfully", stages));
    }

    /**
     * POST /dashboard/stages/{id}/etudiants/{etudiantId} - Assigne un étudiant à un stage
     */
    @PostMapping("/{id}/etudiants/{etudiantId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENCADRANT')")
    public ResponseEntity<GenericDTOs.ApiResponse<String>> assignEtudiantToStage(
            @PathVariable Long id,
            @PathVariable Long etudiantId) {
        stagePFEService.assignEtudiantToStage(id, etudiantId);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiant assigned successfully", ""));
    }

    /**
     * DELETE /dashboard/stages/{id}/etudiants/{etudiantId} - Retire un étudiant d'un stage
     */
    @DeleteMapping("/{id}/etudiants/{etudiantId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENCADRANT')")
    public ResponseEntity<GenericDTOs.ApiResponse<String>> removeEtudiantFromStage(
            @PathVariable Long id,
            @PathVariable Long etudiantId) {
        stagePFEService.removeEtudiantFromStage(id, etudiantId);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiant removed successfully", ""));
    }
}
