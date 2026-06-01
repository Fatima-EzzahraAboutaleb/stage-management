# PFE Management System - Application Web Complète

Application web professionnelle de gestion des stages PFE (Projet de Fin d'Études) construite avec **Spring Boot** (backend) et **Angular** (frontend).

## Vue d'ensemble du Projet

Système complet de gestion des stages universitaires permettant :
- **Authentification sécurisée** avec JWT
- **Gestion des stages PFE** (création, modification, suppression)
- **Gestion des étudiants** par filière
- **Gestion des entreprises partenaires**
- **Gestion des encadrants académiques**
- **Gestion des filières**
- **Dashboard statistique** avec graphiques
- **Gestion des rôles** (ADMIN, ENCADRANT, ETUDIANT)
- **Recherche et filtres** dynamiques
- **Export PDF** (documents et rapports)
- **Mode sombre/clair** avec sauvegarde des préférences

---

## 🏗️ Architecture

### Stack Technique

**Frontend:**
- Angular 18+ avec TypeScript
- Tailwind CSS 4 pour le styling
- Lucide Icons pour les icônes
- Chart.js pour les graphiques
- jsPDF pour export PDF
- NgxToastr pour les notifications
- RxJS pour la gestion réactive

**Backend:**
- Spring Boot 3.x
- Spring Data JPA avec Hibernate
- Spring Security + JWT (jjwt)
- MySQL 8+
- Lombok pour réduire le boilerplate
- Swagger/OpenAPI pour documentation
- MapStruct pour mapping DTO

---

## 📋 Entités et Relations

```
┌─────────────────────────────────────────────────────────┐
│                    MODELE DE DONNEES                     │
├─────────────────────────────────────────────────────────┤
│                                                           │
│  Filière (1) ──────┬──── (N) Étudiant                   │
│     │              └──── (1) ResponsableFilière         │
│     │                                                     │
│  Étudiant ───────────────── (N) StagePFE (M:N)         │
│                                                           │
│  StagePFE (N) ──── (1) Entreprise                       │
│      │                                                    │
│      └──── (1) EncadrantAcademique                      │
│                                                           │
│  User (Authentification)                                 │
│     ├── ADMIN                                            │
│     ├── ENCADRANT                                        │
│     └── ETUDIANT                                         │
│                                                           │
└─────────────────────────────────────────────────────────┘
```

---

## 🚀 Installation et Configuration

### Prérequis

- **Java 17+** (pour Spring Boot)
- **Node.js 18+** et npm/pnpm (pour Angular)
- **MySQL 8.0+**
- **Git**

### Backend Spring Boot

#### 1. Configuration MySQL

```bash
# Créer la base de données
mysql -u root -p < backend/src/main/resources/init.sql

# Ou manuellement :
CREATE DATABASE pfe_management;
```

#### 2. Configuration Application

Éditer `backend/src/main/resources/application.yml` :

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pfe_management
    username: root
    password: <votre_password>
  jpa:
    hibernate:
      ddl-auto: update
```

#### 3. Installation et Démarrage

```bash
cd backend

# Build du projet
mvn clean package

# Ou run directement
mvn spring-boot:run

# Le serveur démarre sur http://localhost:8080
```

**Endpoints disponibles:**
- API REST: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/api/swagger-ui.html`

---

### Frontend Angular

#### 1. Installation des dépendances

```bash
cd frontend

# Avec npm
npm install

# Ou avec pnpm (recommandé)
pnpm install
```

#### 2. Démarrage du serveur de développement

```bash
npm start
# ou
pnpm start

# Le serveur démarre sur http://localhost:4200
```

#### 3. Build pour production

```bash
npm run build
# Les fichiers sont dans le répertoire dist/
```

---

## 🔐 Authentification

### Identifiants de Démonstration

```
ADMIN
  Email: admin@pfe.com
  Password: password
  Rôle: ADMIN

ENCADRANT
  Email: encadrant@pfe.com
  Password: password
  Rôle: ENCADRANT

ÉTUDIANT
  Email: etudiant@pfe.com
  Password: password
  Rôle: ETUDIANT
```

### JWT Token

- Le token est stocké dans `localStorage` sous la clé `token`
- Durée de validité: **24 heures**
- Format: `Authorization: Bearer <token>`

---

## 📚 Structure du Projet

### Backend

```
backend/
├── src/main/java/com/pfe/
│   ├── controller/          # REST Controllers
│   │   ├── AuthController
│   │   ├── StagePFEController
│   │   └── OtherControllers
│   ├── service/             # Business Logic
│   │   ├── AuthService
│   │   ├── StagePFEService
│   │   ├── EtudiantService
│   │   ├── GenericServices
│   │   └── DashboardService
│   ├── repository/          # JPA Repositories
│   │   └── RepositoryInterfaces
│   ├── entity/              # JPA Entities
│   │   ├── User
│   │   ├── Filière
│   │   ├── Étudiant
│   │   ├── StagePFE
│   │   ├── Entreprise
│   │   ├── EncadrantAcademique
│   │   └── ResponsableFilière
│   ├── dto/                 # Data Transfer Objects
│   │   ├── AuthDTO
│   │   ├── StagePFEDTO
│   │   ├── EtudiantDTO
│   │   └── GenericDTOs
│   ├── security/            # JWT & Security
│   │   ├── JwtTokenProvider
│   │   ├── JwtAuthenticationFilter
│   │   └── CustomUserDetailsService
│   ├── config/              # Spring Configuration
│   │   └── SecurityConfig
│   └── PfeManagementApplication.java
├── src/main/resources/
│   ├── application.yml
│   └── init.sql
└── pom.xml
```

### Frontend

```
frontend/
├── src/
│   ├── app/
│   │   ├── core/
│   │   │   ├── guards/           # Route Guards
│   │   │   │   └── auth.guard.ts
│   │   │   ├── services/         # API Services
│   │   │   │   ├── auth.service.ts
│   │   │   │   ├── api.service.ts
│   │   │   │   └── data.service.ts
│   │   │   ├── models/           # TypeScript Models
│   │   │   │   ├── auth.model.ts
│   │   │   │   └── stage.model.ts
│   │   │   └── interceptors/
│   │   ├── shared/
│   │   │   └── components/       # Shared Components
│   │   │       ├── sidebar/
│   │   │       └── navbar/
│   │   ├── features/             # Feature Modules
│   │   │   ├── auth/
│   │   │   │   ├── login/
│   │   │   │   └── register/
│   │   │   ├── dashboard/
│   │   │   ├── stages/
│   │   │   ├── etudiants/
│   │   │   ├── entreprises/
│   │   │   ├── filieres/
│   │   │   ├── encadrants/
│   │   │   ├── profile/
│   │   │   └── settings/
│   │   ├── layout/
│   │   │   └── main-layout/
│   │   ├── app.routes.ts
│   │   ├── app.config.ts
│   │   └── app.component.ts
│   ├── styles.css               # Global Styles
│   └── main.ts
├── tailwind.config.js
├── tsconfig.json
├── angular.json
└── package.json
```

---

## 🔌 API REST - Endpoints Principaux

### Authentification

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| POST | `/auth/login` | Connexion utilisateur |
| POST | `/auth/register` | Inscription nouvel utilisateur |

### Stages PFE

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/dashboard/stages` | Récupère tous les stages |
| GET | `/dashboard/stages/{id}` | Détail d'un stage |
| POST | `/dashboard/stages` | Crée un stage |
| PUT | `/dashboard/stages/{id}` | Modifie un stage |
| DELETE | `/dashboard/stages/{id}` | Supprime un stage |
| GET | `/dashboard/stages/search/{keyword}` | Recherche stages |
| POST | `/dashboard/stages/{id}/etudiants/{etudiantId}` | Assigne étudiant |

### Étudiants

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/dashboard/etudiants` | Liste étudiants |
| GET | `/dashboard/etudiants/{id}` | Détail étudiant |
| POST | `/dashboard/etudiants` | Crée étudiant |
| PUT | `/dashboard/etudiants/{id}` | Modifie étudiant |
| DELETE | `/dashboard/etudiants/{id}` | Supprime étudiant |

### Autres Ressources

- `/dashboard/entreprises` - Gestion des entreprises
- `/dashboard/filieres` - Gestion des filières
- `/dashboard/encadrants` - Gestion des encadrants
- `/dashboard/stats` - Statistiques du dashboard

---

## 🎨 Design et UX

### Palette de Couleurs

```
Primaire:   #0f172a (Bleu Marin)
Secondaire: #ffffff (Blanc)
Accent:     #3b82f6 (Bleu Ciel)
Succès:     #10b981 (Vert)
Alerte:     #f59e0b (Orange)
Erreur:     #ef4444 (Rouge)
```

### Fonctionnalités UI

✅ **Mode Sombre/Clair** - Bascule avec stockage localStorage
✅ **Animations fluides** - Transitions CSS et Framer Motion
✅ **Responsive Design** - Mobile, Tablet, Desktop optimisé
✅ **Loading States** - Spinners et skeleton loading
✅ **Empty States** - Messages personnalisés
✅ **Toast Notifications** - Retours utilisateur
✅ **Badges et Statuts** - Visuels clairs
✅ **Pagination** - Navigation facile dans les listes

---

## 👨‍💼 Gestion des Rôles et Permissions

### ADMIN
- Accès complet à toutes les fonctionnalités
- Création/modification/suppression de tous les éléments
- Gestion des filières
- Gestion des encadrants

### ENCADRANT
- Consultation et création de stages
- Gestion des étudiants affectés
- Consultation des entreprises
- Pas d'accès à la gestion des filières/encadrants

### ÉTUDIANT
- Consultation des stages
- Accès à son profil
- Consultation des autres ressources (lecture seule)

---

## 📝 Utilisation

### Workflow Typique

1. **Login** - Authentification avec email/password
2. **Dashboard** - Vue d'ensemble des statistiques
3. **Créer Stage** - Remplir le formulaire avec infos du stage
4. **Affecter Étudiants** - Assigner les étudiants au stage
5. **Suivre Progression** - Mettre à jour le statut
6. **Générer Rapport** - Export PDF

### Gestion des Stages

1. Accéder à la section "Stages"
2. Cliquer sur "Nouveau Stage"
3. Remplir tous les champs requis
4. Sélectionner entreprise et encadrant
5. Affecter les étudiants
6. Sauvegarder

---

## 🔧 Développement

### Ajouter une Nouvelle Entité

#### Backend

1. Créer l'Entity class dans `entity/`
2. Créer le Repository dans `repository/`
3. Créer le DTO dans `dto/`
4. Créer le Service dans `service/`
5. Créer le Controller dans `controller/`

#### Frontend

1. Créer l'interface Model dans `core/models/`
2. Créer le Service dans `core/services/`
3. Créer les Components dans `features/`
4. Ajouter les routes dans `app.routes.ts`

---

## 🧪 Testing

### Backend

```bash
cd backend
mvn test
```

### Frontend

```bash
cd frontend
npm test
```

---

## 📦 Déploiement

### Backend (Vercel/Heroku)

```bash
# Build JAR
mvn clean package

# Déployer le JAR
```

### Frontend (Vercel)

```bash
cd frontend
npm run build
# Déployer le contenu de dist/
```

---

## 🐛 Troubleshooting

### "Cannot connect to MySQL"
- Vérifier que MySQL est lancé
- Vérifier les credentials dans `application.yml`

### "CORS error"
- Vérifier que `allowed-origins` dans `SecurityConfig` inclut le port du frontend
- Vérifier les headers dans les requests Angular

### "Token expired"
- Effacer localStorage et se reconnecter
- Augmenter la durée du token si nécessaire

### "Port already in use"
- Backend: Changer le port dans `application.yml`
- Frontend: Changer le port avec `ng serve --port 4300`

---

## 📖 Documentation

- **Swagger/OpenAPI**: http://localhost:8080/api/swagger-ui.html
- **Angular Docs**: https://angular.io/docs
- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Tailwind CSS**: https://tailwindcss.com/docs

---

## 👥 Contribution

Ce projet est une démonstration pédagogique. Suggestions et amélirations bienvenues!

---

## 📄 Licence

Projet universitaire - Libre d'utilisation

---

## 👨‍💻 Auteur

**Développé pour la gestion des stages PFE**
*2024*

---

## 📞 Support

Pour toute question ou problème :
1. Vérifier la section Troubleshooting
2. Consulter les logs (backend: console, frontend: DevTools)
3. Vérifier la documentation API (Swagger)

---

## ✨ Fonctionnalités Futures

- [ ] Export Excel des listes
- [ ] Calendrier des stages
- [ ] Système de notation/évaluation
- [ ] Notifications en temps réel (WebSocket)
- [ ] Intégration avec mail (confirmations)
- [ ] Rapports avancés (PDF personnalisés)
- [ ] Gestion des documents (uploads)
- [ ] API mobile avec OAuth2

---

**Bon développement! 🚀**
