# 🗄️ Guide de Configuration de la Base de Données

## Installation MySQL

### Windows
1. Télécharger MySQL 8.0+ depuis https://dev.mysql.com/downloads/mysql/
2. Installer avec l'installateur
3. Configurer MySQL Server (port 3306, root password)
4. Vérifier l'installation: `mysql --version`

### macOS
```bash
brew install mysql
brew services start mysql
```

### Linux (Ubuntu/Debian)
```bash
sudo apt-get update
sudo apt-get install mysql-server
sudo mysql_secure_installation
```

---

## Initialisation de la Base de Données

### Méthode 1 : Fichier SQL (Recommandé)

```bash
# 1. Accéder au répertoire backend
cd backend

# 2. Créer la base et importer les données
mysql -u root -p < src/main/resources/init.sql

# 3. Vérifier
mysql -u root -p -e "USE pfe_management; SHOW TABLES;"
```

### Méthode 2 : Commandes Directes

```bash
# Se connecter à MySQL
mysql -u root -p

# Créer la base
CREATE DATABASE pfe_management;
USE pfe_management;

# Copier le contenu de init.sql et l'exécuter

# Vérifier
SHOW TABLES;
SELECT COUNT(*) FROM users;
```

---

## Structure de la Base de Données

### Diagramme des Relations

```
Users (Authentification)
│
├── Filieres
│   ├── ResponsablesFilieres (1:1)
│   └── Etudiants (1:N)
│
├── Etudiants (N:M) ←→ StagesPFE
│
├── StagesPFE
│   ├── Entreprises (N:1)
│   └── EncadrantsAcademiques (N:1)
│
└── Entreprises
    └── Encadrants_Academiques
```

### Tables

#### 1. users
Utilisateurs et authentification
```sql
id BIGINT PRIMARY KEY AUTO_INCREMENT
email VARCHAR(255) NOT NULL UNIQUE
password VARCHAR(255) NOT NULL
nom VARCHAR(100) NOT NULL
prenom VARCHAR(100) NOT NULL
role ENUM('ADMIN', 'ENCADRANT', 'ETUDIANT')
active BOOLEAN DEFAULT TRUE
```

#### 2. filieres
Filières d'études
```sql
id BIGINT PRIMARY KEY AUTO_INCREMENT
intitule VARCHAR(255) NOT NULL UNIQUE
responsable_id BIGINT FOREIGN KEY
```

#### 3. responsables_filieres
Responsables de filières
```sql
id BIGINT PRIMARY KEY AUTO_INCREMENT
nom VARCHAR(100) NOT NULL
prenom VARCHAR(100) NOT NULL
grade VARCHAR(100) NOT NULL
email VARCHAR(255) NOT NULL UNIQUE
telephone VARCHAR(20) NOT NULL
```

#### 4. etudiants
Étudiants
```sql
id BIGINT PRIMARY KEY AUTO_INCREMENT
email VARCHAR(255) NOT NULL UNIQUE
cne VARCHAR(20) NOT NULL UNIQUE
nom VARCHAR(100) NOT NULL
prenom VARCHAR(100) NOT NULL
telephone VARCHAR(20) NOT NULL
filiere_id BIGINT NOT NULL FOREIGN KEY
```

#### 5. entreprises
Entreprises partenaires
```sql
id BIGINT PRIMARY KEY AUTO_INCREMENT
nom VARCHAR(255) NOT NULL UNIQUE
adresse TEXT NOT NULL
telephone VARCHAR(20) NOT NULL
email VARCHAR(255) NOT NULL UNIQUE
ville VARCHAR(100) NOT NULL
pays VARCHAR(100) NOT NULL
responsable_email VARCHAR(255) NOT NULL
encadrant_entreprise_email VARCHAR(255) NOT NULL
encadrant_entreprise_telephone VARCHAR(20) NOT NULL
```

#### 6. encadrants_academiques
Encadrants académiques
```sql
id BIGINT PRIMARY KEY AUTO_INCREMENT
nom VARCHAR(100) NOT NULL
departement VARCHAR(100) NOT NULL
etablissement VARCHAR(255) NOT NULL
email VARCHAR(255) NOT NULL UNIQUE
```

#### 7. stages_pfe
Stages PFE
```sql
id BIGINT PRIMARY KEY AUTO_INCREMENT
sujet VARCHAR(255) NOT NULL
date_debut DATE NOT NULL
date_fin DATE NOT NULL
description LONGTEXT NOT NULL
objectifs LONGTEXT NOT NULL
solution LONGTEXT NOT NULL
demarche_projet LONGTEXT
outils_utilises LONGTEXT
annee_universitaire VARCHAR(20) NOT NULL
statut ENUM('PROPOSE', 'EN_COURS', 'TERMINE', 'ANNULE')
entreprise_id BIGINT NOT NULL FOREIGN KEY
encadrant_id BIGINT NOT NULL FOREIGN KEY
```

#### 8. stage_etudiants (Junction Table)
Relation Many-to-Many entre stages et étudiants
```sql
stage_id BIGINT NOT NULL FOREIGN KEY
etudiant_id BIGINT NOT NULL FOREIGN KEY
PRIMARY KEY (stage_id, etudiant_id)
```

---

## Gestion des Données

### Ajouter un nouvel utilisateur

```sql
INSERT INTO users (email, password, nom, prenom, role)
VALUES ('nouveau@pfe.com', 
        '$2a$10$...', -- Hash BCrypt
        'Dupont', 
        'Jean', 
        'ADMIN');
```

### Ajouter une nouvelle filière

```sql
INSERT INTO filieres (intitule)
VALUES ('Informatique'),
       ('Génie Logiciel'),
       ('Réseaux');
```

### Ajouter un étudiant

```sql
INSERT INTO etudiants (email, cne, nom, prenom, telephone, filiere_id)
VALUES ('etudiant@example.com', 'CNE123', 'Alaoui', 'Mohammed', '0612345678', 1);
```

### Ajouter une entreprise

```sql
INSERT INTO entreprises 
(nom, adresse, telephone, email, ville, pays, responsable_email, 
 encadrant_entreprise_email, encadrant_entreprise_telephone)
VALUES ('TechCorp', '123 Rue Tech', '0123456789', 'info@techcorp.fr', 
        'Paris', 'France', 'ceo@techcorp.fr', 'encadrant@techcorp.fr', '0198765432');
```

### Ajouter un stage

```sql
INSERT INTO stages_pfe 
(sujet, date_debut, date_fin, description, objectifs, solution, 
 annee_universitaire, statut, entreprise_id, encadrant_id)
VALUES ('Développement Web', '2024-02-01', '2024-05-31', 
        'Développement d\'une app web moderne...', 
        'Créer une app scalable', 
        'Angular + Spring Boot',
        '2023-2024', 'EN_COURS', 1, 1);
```

### Assigner un étudiant à un stage

```sql
INSERT INTO stage_etudiants (stage_id, etudiant_id)
VALUES (1, 1), (1, 2);
```

---

## Sauvegarde et Restauration

### Sauvegarder la base

```bash
# Exporter toute la base
mysqldump -u root -p pfe_management > backup.sql

# Exporter une table spécifique
mysqldump -u root -p pfe_management users > users_backup.sql
```

### Restaurer la base

```bash
# À partir d'une sauvegarde complète
mysql -u root -p pfe_management < backup.sql

# À partir d'une table spécifique
mysql -u root -p pfe_management < users_backup.sql
```

---

## Requêtes Utiles

### Compter les enregistrements

```sql
SELECT 
    (SELECT COUNT(*) FROM users) as total_users,
    (SELECT COUNT(*) FROM etudiants) as total_etudiants,
    (SELECT COUNT(*) FROM stages_pfe) as total_stages,
    (SELECT COUNT(*) FROM entreprises) as total_entreprises;
```

### Stages par statut

```sql
SELECT 
    statut,
    COUNT(*) as nombre
FROM stages_pfe
GROUP BY statut;
```

### Étudiants par filière

```sql
SELECT 
    f.intitule,
    COUNT(e.id) as nombre
FROM filieres f
LEFT JOIN etudiants e ON f.id = e.filiere_id
GROUP BY f.intitule;
```

### Stages par entreprise

```sql
SELECT 
    e.nom,
    COUNT(s.id) as nombre_de_stages
FROM entreprises e
LEFT JOIN stages_pfe s ON e.id = s.entreprise_id
GROUP BY e.nom;
```

### Détails d'un stage avec relations

```sql
SELECT 
    s.sujet,
    s.statut,
    ent.nom as entreprise,
    enc.nom as encadrant,
    GROUP_CONCAT(CONCAT(et.nom, ' ', et.prenom) SEPARATOR ', ') as etudiants
FROM stages_pfe s
JOIN entreprises ent ON s.entreprise_id = ent.id
JOIN encadrants_academiques enc ON s.encadrant_id = enc.id
LEFT JOIN stage_etudiants se ON s.id = se.stage_id
LEFT JOIN etudiants et ON se.etudiant_id = et.id
GROUP BY s.id;
```

---

## Performance et Optimisation

### Index (créés automatiquement)

```sql
-- Vérifier les index
SHOW INDEXES FROM etudiants;

-- Index de recherche
CREATE INDEX idx_etudiants_nom ON etudiants(nom);
CREATE INDEX idx_stages_sujet ON stages_pfe(sujet);
CREATE INDEX idx_entreprises_nom ON entreprises(nom);
```

### Statistiques de la base

```sql
-- Taille de la base
SELECT 
    table_name,
    ROUND(((data_length + index_length) / 1024 / 1024), 2) AS size_MB
FROM information_schema.tables
WHERE table_schema = 'pfe_management';

-- Nombre total de lignes
SELECT 
    table_name,
    table_rows
FROM information_schema.tables
WHERE table_schema = 'pfe_management';
```

---

## Maintenance

### Vérifier l'intégrité

```sql
-- Vérifier les tables
CHECK TABLE users, etudiants, stages_pfe;

-- Réparer si nécessaire
REPAIR TABLE etudiants;

-- Optimiser les tables
OPTIMIZE TABLE stages_pfe;
```

### Nettoyer les données de test

```bash
# Supprimer tout et recommencer
mysql -u root -p -e "DROP DATABASE pfe_management;"
mysql -u root -p < init.sql
```

---

## Troubleshooting Base de Données

### Erreur "Access denied"
```bash
# Vérifier les credentials
mysql -u root -p
# Tester la connexion
```

### Table verrouillée
```sql
-- Déverrouiller
UNLOCK TABLES;

-- Voir les processus actifs
SHOW PROCESSLIST;
KILL <process_id>;
```

### Espace disque plein
```sql
-- Compresser les logs
PURGE BINARY LOGS BEFORE DATE_SUB(NOW(), INTERVAL 7 DAY);

-- Nettoyer les données supprimées
OPTIMIZE TABLE etudiants;
```

---

## Configuration Spring Boot

Vérifier `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pfe_management
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: update  # Mettre à 'update' seulement en dev
    show-sql: false
```

---

## Prochaines Étapes

1. ✅ Créer la base de données
2. ✅ Vérifier la connexion
3. → Démarrer l'application Spring Boot
4. → L'app crée les tables automatiquement
5. → Les données de test sont disponibles

**Besoin d'aide? Consultez le README.md pour la documentation complète!**
