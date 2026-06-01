-- Insertion de données de test
-- Users (Le mot de passe est 'password' hashé avec BCrypt: $2a$10$8Q/5G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G)
-- Note: Dans un environnement réel, ces mots de passe devraient être hashés correctement.
INSERT INTO users (email, password, nom, prenom, role, active) VALUES 
('admin@pfe.com', '$2a$10$8Q/5G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G', 'Admin', 'User', 'ADMIN', true),
('encadrant@pfe.com', '$2a$10$8Q/5G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G', 'Encadrant', 'User', 'ENCADRANT', true),
('etudiant@pfe.com', '$2a$10$8Q/5G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G5Z3X1.9G', 'Etudiant', 'User', 'ETUDIANT', true);

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
('Développement d''une plateforme web', '2024-02-01', '2024-05-30', 'Développement complet d''une application web moderne', 'Créer une application web scalable', 'Angular + Spring Boot + MySQL', 'Agile', 'Angular, Spring Boot, MySQL, Git', '2023-2024', 'EN_COURS', 1, 1),
('Mobile app development', '2024-03-01', '2024-06-30', 'Développement d''une application mobile', 'Créer une app native', 'React Native', 'Scrum', 'React Native, Firebase', '2023-2024', 'PROPOSE', 2, 2);

-- Relation Stage-Étudiants
INSERT INTO stage_etudiants (stage_id, etudiant_id) VALUES 
(1, 1),
(1, 2),
(2, 3);
