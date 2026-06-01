package com.pfe.controller;

import com.pfe.dto.EtudiantDTO;
import com.pfe.dto.GenericDTOs;
import com.pfe.service.EtudiantService;
import com.pfe.service.GenericServices;
import com.pfe.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// ========== ETUDIANT CONTROLLER ==========
@RestController
@RequestMapping("/dashboard/etudiants")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
class EtudiantController {

    private final EtudiantService etudiantService;

    @GetMapping
    public ResponseEntity<GenericDTOs.ApiResponse<List<EtudiantDTO>>> getAllEtudiants() {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiants retrieved", etudiantService.getAllEtudiants()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericDTOs.ApiResponse<EtudiantDTO>> getEtudiantById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiant retrieved", etudiantService.getEtudiantById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENCADRANT')")
    public ResponseEntity<GenericDTOs.ApiResponse<EtudiantDTO>> createEtudiant(@RequestBody EtudiantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericDTOs.ApiResponse<>("Etudiant created", etudiantService.createEtudiant(dto))
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<EtudiantDTO>> updateEtudiant(
            @PathVariable Long id, @RequestBody EtudiantDTO dto) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiant updated", etudiantService.updateEtudiant(id, dto)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<String>> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiant deleted", ""));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<GenericDTOs.ApiResponse<List<EtudiantDTO>>> searchEtudiants(@PathVariable String keyword) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiants found", etudiantService.searchEtudiants(keyword)));
    }

    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<GenericDTOs.ApiResponse<List<EtudiantDTO>>> getEtudiantsByFiliere(@PathVariable Long filiereId) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Etudiants retrieved", etudiantService.getEtudiantsByFiliere(filiereId)));
    }
}

// ========== FILIERE CONTROLLER ==========
@RestController
@RequestMapping("/dashboard/filieres")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
class FiliereController {

    private final GenericServices genericServices;

    @GetMapping
    public ResponseEntity<GenericDTOs.ApiResponse<List<GenericDTOs.FiliereDTO>>> getAllFilieres() {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Filieres retrieved", genericServices.getAllFilieres()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.FiliereDTO>> getFiliereById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Filiere retrieved", genericServices.getFiliereById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.FiliereDTO>> createFiliere(
            @RequestBody GenericDTOs.FiliereDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericDTOs.ApiResponse<>("Filiere created", genericServices.createFiliere(dto))
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.FiliereDTO>> updateFiliere(
            @PathVariable Long id, @RequestBody GenericDTOs.FiliereDTO dto) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Filiere updated", genericServices.updateFiliere(id, dto)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<String>> deleteFiliere(@PathVariable Long id) {
        genericServices.deleteFiliere(id);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Filiere deleted", ""));
    }
}

// ========== ENTREPRISE CONTROLLER ==========
@RestController
@RequestMapping("/dashboard/entreprises")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
class EntrepriseController {

    private final GenericServices genericServices;

    @GetMapping
    public ResponseEntity<GenericDTOs.ApiResponse<List<GenericDTOs.EntrepriseDTO>>> getAllEntreprises() {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Entreprises retrieved", genericServices.getAllEntreprises()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.EntrepriseDTO>> getEntrepriseById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Entreprise retrieved", genericServices.getEntrepriseById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENCADRANT')")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.EntrepriseDTO>> createEntreprise(
            @RequestBody GenericDTOs.EntrepriseDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericDTOs.ApiResponse<>("Entreprise created", genericServices.createEntreprise(dto))
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.EntrepriseDTO>> updateEntreprise(
            @PathVariable Long id, @RequestBody GenericDTOs.EntrepriseDTO dto) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Entreprise updated", genericServices.updateEntreprise(id, dto)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<String>> deleteEntreprise(@PathVariable Long id) {
        genericServices.deleteEntreprise(id);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Entreprise deleted", ""));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<GenericDTOs.ApiResponse<List<GenericDTOs.EntrepriseDTO>>> searchEntreprises(
            @PathVariable String keyword) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Entreprises found", genericServices.searchEntreprises(keyword)));
    }
}

// ========== ENCADRANT CONTROLLER ==========
@RestController
@RequestMapping("/dashboard/encadrants")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
class EncadrantController {

    private final GenericServices genericServices;

    @GetMapping
    public ResponseEntity<GenericDTOs.ApiResponse<List<GenericDTOs.EncadrantDTO>>> getAllEncadrants() {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Encadrants retrieved", genericServices.getAllEncadrants()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.EncadrantDTO>> getEncadrantById(@PathVariable Long id) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Encadrant retrieved", genericServices.getEncadrantById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.EncadrantDTO>> createEncadrant(
            @RequestBody GenericDTOs.EncadrantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new GenericDTOs.ApiResponse<>("Encadrant created", genericServices.createEncadrant(dto))
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.EncadrantDTO>> updateEncadrant(
            @PathVariable Long id, @RequestBody GenericDTOs.EncadrantDTO dto) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Encadrant updated", genericServices.updateEncadrant(id, dto)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericDTOs.ApiResponse<String>> deleteEncadrant(@PathVariable Long id) {
        genericServices.deleteEncadrant(id);
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Encadrant deleted", ""));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<GenericDTOs.ApiResponse<List<GenericDTOs.EncadrantDTO>>> searchEncadrants(
            @PathVariable String keyword) {
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Encadrants found", genericServices.searchEncadrants(keyword)));
    }
}

// ========== DASHBOARD CONTROLLER ==========
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<GenericDTOs.ApiResponse<GenericDTOs.DashboardStatsDTO>> getDashboardStats() {
        GenericDTOs.DashboardStatsDTO stats = dashboardService.getDashboardStats();
        return ResponseEntity.ok(new GenericDTOs.ApiResponse<>("Dashboard stats retrieved", stats));
    }
}
