package com.pfe.repository;

import com.pfe.entity.ResponsableFiliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsableFiliereRepository extends JpaRepository<ResponsableFiliere, Long> {
    Optional<ResponsableFiliere> findByEmail(String email);
}
