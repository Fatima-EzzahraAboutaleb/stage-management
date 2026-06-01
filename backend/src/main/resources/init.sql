-- Création de la base de données
CREATE DATABASE IF NOT EXISTS pfe_management;
USE pfe_management;

-- Création des tables

-- Table Users
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table Responsables Filières
CREATE TABLE responsables_filieres (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    grade VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telephone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table Filières
CREATE TABLE filieres (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    intitule VARCHAR(255) NOT NULL UNIQUE,
    responsable_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (responsable_id) REFERENCES responsables_filieres(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table Étudiants
CREATE TABLE etudiants (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    cne VARCHAR(20) NOT NULL UNIQUE,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    filiere_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (filiere_id) REFERENCES filieres(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table Entreprises
CREATE TABLE entreprises (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL UNIQUE,
    adresse TEXT NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    ville VARCHAR(100) NOT NULL,
    pays VARCHAR(100) NOT NULL,
    responsable_email VARCHAR(255) NOT NULL,
    encadrant_entreprise_email VARCHAR(255) NOT NULL,
    encadrant_entreprise_telephone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table Encadrants Académiques
CREATE TABLE encadrants_academiques (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    departement VARCHAR(100) NOT NULL,
    etablissement VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table Stages PFE
CREATE TABLE stages_pfe (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sujet VARCHAR(255) NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    description LONGTEXT NOT NULL,
    objectifs LONGTEXT NOT NULL,
    solution LONGTEXT NOT NULL,
    demarche_projet LONGTEXT,
    outils_utilises LONGTEXT,
    annee_universitaire VARCHAR(20) NOT NULL,
    statut VARCHAR(50) NOT NULL,
    entreprise_id BIGINT NOT NULL,
    encadrant_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (entreprise_id) REFERENCES entreprises(id),
    FOREIGN KEY (encadrant_id) REFERENCES encadrants_academiques(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table Stage Étudiants (Relation Many-to-Many)
CREATE TABLE stage_etudiants (
    stage_id BIGINT NOT NULL,
    etudiant_id BIGINT NOT NULL,
    PRIMARY KEY (stage_id, etudiant_id),
    FOREIGN KEY (stage_id) REFERENCES stages_pfe(id) ON DELETE CASCADE,
    FOREIGN KEY (etudiant_id) REFERENCES etudiants(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insertion de données de test
-- Users
INSERT INTO users (email, password, nom, prenom, role) VALUES 
('admin@pfe.com', '$2a$10$8Q/5G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G', 'Admin', 'User', 'ADMIN'),
('encadrant@pfe.com', '$2a$10$8Q/5G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G', 'Encadrant', 'User', 'ENCADRANT'),
('etudiant@pfe.com', '$2a$10$8Q/5G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G', 'Etudiant', 'User', 'ETUDIANT');

-- Responsables
INSERT INTO responsables_filieres (nom, prenom, grade, email, telephone) VALUES 
('Dupont', 'Jean', 'Professeur', 'dupont@univ.fr', '0612345678');

-- Filières
INSERT INTO filieres (intitule, responsable_id) VALUES 
('Informatique', 1),
('Génie Logiciel', 1),
('Réseaux et Sécurité', 1);

-- Encadrants
INSERT INTO encadrants_academiques (nom, departement, etablissement, email) VALUES 
('Martin', 'Informatique', 'Université X', 'martin@univ.fr'),
('Legrand', 'Informatique', 'Université X', 'legrand@univ.fr');

-- Entreprises
INSERT INTO entreprises (nom, adresse, telephone, email, ville, pays, responsable_email, encadrant_entreprise_email, encadrant_entreprise_telephone) VALUES 
('TechCorp', '123 Rue de la Tech', '0123456789', 'info@techcorp.fr', 'Paris', 'France', 'ceo@techcorp.fr', 'encadrant@techcorp.fr', '0198765432'),
('DataSystems', '456 Boulevard Data', '0234567890', 'contact@datasys.fr', 'Lyon', 'France', 'ceo@datasys.fr', 'responsable@datasys.fr', '0287654321');

-- Étudiants
INSERT INTO etudiants (email, cne, nom, prenom, telephone, filiere_id) VALUES 
('etudiant1@example.com', 'CNE001', 'Alaoui', 'Mohammed', '0612345678', 1),
('etudiant2@example.com', 'CNE002', 'Benali', 'Fatima', '0623456789', 1),
('etudiant3@example.com', 'CNE003', 'Cherif', 'Ahmed', '0634567890', 2);

-- Stages
INSERT INTO stages_pfe (sujet, date_debut, date_fin, description, objectifs, solution, demarche_projet, outils_utilises, annee_universitaire, statut, entreprise_id, encadrant_id) VALUES 
('Développement d\'une plateforme web', '2024-02-01', '2024-05-30', 'Développement complet d\'une application web moderne', 'Créer une application web scalable', 'Angular + Spring Boot + MySQL', 'Agile', 'Angular, Spring Boot, MySQL, Git', '2023-2024', 'EN_COURS', 1, 1),
('Mobile app development', '2024-03-01', '2024-06-30', 'Développement d\'une application mobile', 'Créer une app native', 'React Native', 'Scrum', 'React Native, Firebase', '2023-2024', 'PROPOSE', 2, 2);

-- Relation Stage-Étudiants
INSERT INTO stage_etudiants (stage_id, etudiant_id) VALUES 
(1, 1),
(1, 2),
(2, 3);

-- Création des index pour les performances
CREATE INDEX idx_etudiants_filiere ON etudiants(filiere_id);
CREATE INDEX idx_stages_entreprise ON stages_pfe(entreprise_id);
CREATE INDEX idx_stages_encadrant ON stages_pfe(encadrant_id);
CREATE INDEX idx_stages_statut ON stages_pfe(statut);
CREATE INDEX idx_users_email ON users(email);
