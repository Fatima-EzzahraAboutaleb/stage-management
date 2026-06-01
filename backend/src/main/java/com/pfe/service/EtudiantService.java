package com.pfe.service;

import com.pfe.dto.EtudiantDTO;
import com.pfe.entity.Etudiant;
import com.pfe.entity.Filiere;
import com.pfe.repository.EtudiantRepository;
import com.pfe.repository.FiliereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final FiliereRepository filiereRepository;

    public List<EtudiantDTO> getAllEtudiants() {
        return etudiantRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EtudiantDTO getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found"));
        return convertToDTO(etudiant);
    }

    public EtudiantDTO createEtudiant(EtudiantDTO dto) {
        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filiere not found"));

        Etudiant etudiant = Etudiant.builder()
                .email(dto.getEmail())
                .cne(dto.getCne())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .telephone(dto.getTelephone())
                .filiere(filiere)
                .build();

        etudiant = etudiantRepository.save(etudiant);
        return convertToDTO(etudiant);
    }

    public EtudiantDTO updateEtudiant(Long id, EtudiantDTO dto) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found"));

        etudiant.setEmail(dto.getEmail());
        etudiant.setCne(dto.getCne());
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setTelephone(dto.getTelephone());

        etudiant = etudiantRepository.save(etudiant);
        return convertToDTO(etudiant);
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    public List<EtudiantDTO> searchEtudiants(String keyword) {
        return etudiantRepository.searchEtudiants(keyword)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EtudiantDTO> getEtudiantsByFiliere(Long filiereId) {
        return etudiantRepository.findByFiliereId(filiereId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EtudiantDTO convertToDTO(Etudiant etudiant) {
        return EtudiantDTO.builder()
                .id(etudiant.getId())
                .email(etudiant.getEmail())
                .cne(etudiant.getCne())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .telephone(etudiant.getTelephone())
                .filiereId(etudiant.getFiliere().getId())
                .filiereNom(etudiant.getFiliere().getIntitule())
                .build();
    }
}
