package com.pfe.service;

import com.pfe.dto.GenericDTOs;
import com.pfe.entity.*;
import com.pfe.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Services génériques pour Filière, Entreprise, Encadrant, et Responsable
 */
@Service
@RequiredArgsConstructor
@Transactional
public class GenericServices {

    private final FiliereRepository filiereRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EncadrantAcademiqueRepository encadrantRepository;
    private final ResponsableFiliereRepository responsableRepository;

    // ============= FILIERE SERVICE =============

    public List<GenericDTOs.FiliereDTO> getAllFilieres() {
        return filiereRepository.findAll().stream()
                .map(this::convertFiliereToDTOo)
                .collect(Collectors.toList());
    }

    public GenericDTOs.FiliereDTO getFiliereById(Long id) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere not found"));
        return convertFiliereToDTOo(filiere);
    }

    public GenericDTOs.FiliereDTO createFiliere(GenericDTOs.FiliereDTO dto) {
        Filiere filiere = Filiere.builder()
                .intitule(dto.getIntitule())
                .build();
        filiere = filiereRepository.save(filiere);
        return convertFiliereToDTOo(filiere);
    }

    public GenericDTOs.FiliereDTO updateFiliere(Long id, GenericDTOs.FiliereDTO dto) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere not found"));
        filiere.setIntitule(dto.getIntitule());
        filiere = filiereRepository.save(filiere);
        return convertFiliereToDTOo(filiere);
    }

    public void deleteFiliere(Long id) {
        filiereRepository.deleteById(id);
    }

    // ============= ENTREPRISE SERVICE =============

    public List<GenericDTOs.EntrepriseDTO> getAllEntreprises() {
        return entrepriseRepository.findAll().stream()
                .map(this::convertEntrepriseToDTOo)
                .collect(Collectors.toList());
    }

    public GenericDTOs.EntrepriseDTO getEntrepriseById(Long id) {
        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise not found"));
        return convertEntrepriseToDTOo(entreprise);
    }

    public GenericDTOs.EntrepriseDTO createEntreprise(GenericDTOs.EntrepriseDTO dto) {
        Entreprise entreprise = Entreprise.builder()
                .nom(dto.getNom())
                .adresse(dto.getAdresse())
                .telephone(dto.getTelephone())
                .email(dto.getEmail())
                .ville(dto.getVille())
                .pays(dto.getPays())
                .responsableEmail(dto.getResponsableEmail())
                .encadrantEntrepriseEmail(dto.getEncadrantEntrepriseEmail())
                .encadrantEntrepriseTelephone(dto.getEncadrantEntrepriseTelephone())
                .build();
        entreprise = entrepriseRepository.save(entreprise);
        return convertEntrepriseToDTOo(entreprise);
    }

    public GenericDTOs.EntrepriseDTO updateEntreprise(Long id, GenericDTOs.EntrepriseDTO dto) {
        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise not found"));

        entreprise.setNom(dto.getNom());
        entreprise.setAdresse(dto.getAdresse());
        entreprise.setTelephone(dto.getTelephone());
        entreprise.setEmail(dto.getEmail());
        entreprise.setVille(dto.getVille());
        entreprise.setPays(dto.getPays());
        entreprise.setResponsableEmail(dto.getResponsableEmail());
        entreprise.setEncadrantEntrepriseEmail(dto.getEncadrantEntrepriseEmail());
        entreprise.setEncadrantEntrepriseTelephone(dto.getEncadrantEntrepriseTelephone());

        entreprise = entrepriseRepository.save(entreprise);
        return convertEntrepriseToDTOo(entreprise);
    }

    public void deleteEntreprise(Long id) {
        entrepriseRepository.deleteById(id);
    }

    public List<GenericDTOs.EntrepriseDTO> searchEntreprises(String keyword) {
        return entrepriseRepository.searchEntreprises(keyword)
                .stream()
                .map(this::convertEntrepriseToDTOo)
                .collect(Collectors.toList());
    }

    // ============= ENCADRANT SERVICE =============

    public List<GenericDTOs.EncadrantDTO> getAllEncadrants() {
        return encadrantRepository.findAll().stream()
                .map(this::convertEncadrantToDTOo)
                .collect(Collectors.toList());
    }

    public GenericDTOs.EncadrantDTO getEncadrantById(Long id) {
        EncadrantAcademique encadrant = encadrantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant not found"));
        return convertEncadrantToDTOo(encadrant);
    }

    public GenericDTOs.EncadrantDTO createEncadrant(GenericDTOs.EncadrantDTO dto) {
        EncadrantAcademique encadrant = EncadrantAcademique.builder()
                .nom(dto.getNom())
                .departement(dto.getDepartement())
                .etablissement(dto.getEtablissement())
                .email(dto.getEmail())
                .build();
        encadrant = encadrantRepository.save(encadrant);
        return convertEncadrantToDTOo(encadrant);
    }

    public GenericDTOs.EncadrantDTO updateEncadrant(Long id, GenericDTOs.EncadrantDTO dto) {
        EncadrantAcademique encadrant = encadrantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant not found"));

        encadrant.setNom(dto.getNom());
        encadrant.setDepartement(dto.getDepartement());
        encadrant.setEtablissement(dto.getEtablissement());
        encadrant.setEmail(dto.getEmail());

        encadrant = encadrantRepository.save(encadrant);
        return convertEncadrantToDTOo(encadrant);
    }

    public void deleteEncadrant(Long id) {
        encadrantRepository.deleteById(id);
    }

    public List<GenericDTOs.EncadrantDTO> searchEncadrants(String keyword) {
        return encadrantRepository.searchEncadrants(keyword)
                .stream()
                .map(this::convertEncadrantToDTOo)
                .collect(Collectors.toList());
    }

    // ============= HELPER CONVERSION METHODS =============

    private GenericDTOs.FiliereDTO convertFiliereToDTOo(Filiere filiere) {
        return GenericDTOs.FiliereDTO.builder()
                .id(filiere.getId())
                .intitule(filiere.getIntitule())
                .responsableId(filiere.getResponsable() != null ? filiere.getResponsable().getId() : null)
                .responsableNom(filiere.getResponsable() != null ? filiere.getResponsable().getNom() + " " + filiere.getResponsable().getPrenom() : null)
                .build();
    }

    private GenericDTOs.EntrepriseDTO convertEntrepriseToDTOo(Entreprise entreprise) {
        return GenericDTOs.EntrepriseDTO.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .adresse(entreprise.getAdresse())
                .telephone(entreprise.getTelephone())
                .email(entreprise.getEmail())
                .ville(entreprise.getVille())
                .pays(entreprise.getPays())
                .responsableEmail(entreprise.getResponsableEmail())
                .encadrantEntrepriseEmail(entreprise.getEncadrantEntrepriseEmail())
                .encadrantEntrepriseTelephone(entreprise.getEncadrantEntrepriseTelephone())
                .build();
    }

    private GenericDTOs.EncadrantDTO convertEncadrantToDTOo(EncadrantAcademique encadrant) {
        return GenericDTOs.EncadrantDTO.builder()
                .id(encadrant.getId())
                .nom(encadrant.getNom())
                .departement(encadrant.getDepartement())
                .etablissement(encadrant.getEtablissement())
                .email(encadrant.getEmail())
                .build();
    }
}
