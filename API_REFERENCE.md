# 📚 Référence API Complète

Documentation complète de tous les endpoints API REST disponibles.

**Base URL:** `http://localhost:8080/api`

---

## 🔐 Authentification

### POST /auth/login
Connexion utilisateur et obtenir un JWT token.

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@pfe.com",
    "password": "password"
  }'
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "type": "Bearer",
    "id": 1,
    "email": "admin@pfe.com",
    "nom": "Admin",
    "prenom": "User",
    "role": "ADMIN"
  }
}
```

**Codes d'erreur:**
- `401` - Identifiants invalides
- `400` - Champs manquants

---

### POST /auth/register
Enregistrer un nouvel utilisateur.

**Request:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "nouveau@pfe.com",
    "password": "secure123",
    "nom": "Dupont",
    "prenom": "Jean",
    "role": "ETUDIANT"
  }'
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "type": "Bearer",
    "id": 4,
    "email": "nouveau@pfe.com",
    "nom": "Dupont",
    "prenom": "Jean",
    "role": "ETUDIANT"
  }
}
```

---

## 📊 Dashboard

### GET /dashboard/stats
Récupère les statistiques du dashboard.

**Request:**
```bash
curl -X GET http://localhost:8080/api/dashboard/stats \
  -H "Authorization: Bearer <TOKEN>"
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Dashboard stats retrieved",
  "data": {
    "totalStages": 5,
    "totalEtudiants": 12,
    "totalEntreprises": 3,
    "totalEncadrants": 4,
    "stagesEnCours": 2,
    "stagesTermines": 3
  }
}
```

---

## 🎓 Stages PFE

### GET /dashboard/stages
Récupère tous les stages.

**Request:**
```bash
curl -X GET http://localhost:8080/api/dashboard/stages \
  -H "Authorization: Bearer <TOKEN>"
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Stages retrieved successfully",
  "data": [
    {
      "id": 1,
      "sujet": "Développement Web",
      "dateDebut": "2024-02-01",
      "dateFin": "2024-05-31",
      "description": "Développement d'une application web",
      "objectifs": "Créer une app scalable",
      "solution": "Angular + Spring Boot",
      "demarcheProjet": "Agile",
      "outilsUtilises": "Angular, Spring, MySQL",
      "anneeUniversitaire": "2023-2024",
      "statut": "EN_COURS",
      "entrepriseId": 1,
      "entrepriseNom": "TechCorp",
      "encadrantId": 1,
      "encadrantNom": "Martin",
      "etudiantIds": [1, 2],
      "etudiantNoms": ["Alaoui Mohammed", "Benali Fatima"]
    }
  ]
}
```

---

### GET /dashboard/stages/{id}
Récupère un stage spécifique.

**Request:**
```bash
curl -X GET http://localhost:8080/api/dashboard/stages/1 \
  -H "Authorization: Bearer <TOKEN>"
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Stage retrieved successfully",
  "data": {
    "id": 1,
    "sujet": "Développement Web",
    // ... tous les champs
  }
}
```

---

### POST /dashboard/stages
Crée un nouveau stage. *(Nécessite ADMIN ou ENCADRANT)*

**Request:**
```bash
curl -X POST http://localhost:8080/api/dashboard/stages \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "sujet": "Nouvelle Application Mobile",
    "dateDebut": "2024-03-01",
    "dateFin": "2024-06-30",
    "description": "Développement d\'une app mobile",
    "objectifs": "Créer une app mobile fonctionnelle",
    "solution": "React Native",
    "demarcheProjet": "Scrum",
    "outilsUtilises": "React Native, Firebase",
    "anneeUniversitaire": "2023-2024",
    "statut": "PROPOSE",
    "entrepriseId": 2,
    "encadrantId": 2,
    "etudiantIds": [3]
  }'
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Stage created successfully",
  "data": {
    "id": 3,
    "sujet": "Nouvelle Application Mobile",
    // ... tous les champs
  }
}
```

---

### PUT /dashboard/stages/{id}
Modifie un stage. *(Nécessite ADMIN)*

**Request:**
```bash
curl -X PUT http://localhost:8080/api/dashboard/stages/1 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "sujet": "Développement Web - Mise à jour",
    "statut": "TERMINE",
    // ... autres champs
  }'
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Stage updated successfully",
  "data": { /* stage modifié */ }
}
```

---

### DELETE /dashboard/stages/{id}
Supprime un stage. *(Nécessite ADMIN)*

**Request:**
```bash
curl -X DELETE http://localhost:8080/api/dashboard/stages/1 \
  -H "Authorization: Bearer <TOKEN>"
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Stage deleted successfully",
  "data": ""
}
```

---

### GET /dashboard/stages/search/{keyword}
Recherche des stages.

**Request:**
```bash
curl -X GET http://localhost:8080/api/dashboard/stages/search/web \
  -H "Authorization: Bearer <TOKEN>"
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Stages found",
  "data": [ /* stages correspondants */ ]
}
```

---

### GET /dashboard/stages/statut/{statut}
Récupère les stages par statut.

**Request:**
```bash
curl -X GET http://localhost:8080/api/dashboard/stages/statut/EN_COURS \
  -H "Authorization: Bearer <TOKEN>"
```

**Statuts disponibles:** `PROPOSE`, `EN_COURS`, `TERMINE`, `ANNULE`

---

### POST /dashboard/stages/{id}/etudiants/{etudiantId}
Assigne un étudiant à un stage.

**Request:**
```bash
curl -X POST http://localhost:8080/api/dashboard/stages/1/etudiants/3 \
  -H "Authorization: Bearer <TOKEN>"
```

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Etudiant assigned successfully",
  "data": ""
}
```

---

### DELETE /dashboard/stages/{id}/etudiants/{etudiantId}
Retire un étudiant d'un stage.

**Request:**
```bash
curl -X DELETE http://localhost:8080/api/dashboard/stages/1/etudiants/3 \
  -H "Authorization: Bearer <TOKEN>"
```

---

## 👥 Étudiants

### GET /dashboard/etudiants
Récupère tous les étudiants.

```bash
curl -X GET http://localhost:8080/api/dashboard/etudiants \
  -H "Authorization: Bearer <TOKEN>"
```

---

### GET /dashboard/etudiants/{id}
Récupère un étudiant spécifique.

```bash
curl -X GET http://localhost:8080/api/dashboard/etudiants/1 \
  -H "Authorization: Bearer <TOKEN>"
```

---

### POST /dashboard/etudiants
Crée un nouvel étudiant. *(ADMIN/ENCADRANT)*

```bash
curl -X POST http://localhost:8080/api/dashboard/etudiants \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newstudent@example.com",
    "cne": "CNE456",
    "nom": "Nouveau",
    "prenom": "Étudiant",
    "telephone": "0612345678",
    "filiereId": 1
  }'
```

---

### PUT /dashboard/etudiants/{id}
Modifie un étudiant. *(ADMIN)*

```bash
curl -X PUT http://localhost:8080/api/dashboard/etudiants/1 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Modifié",
    // ... autres champs
  }'
```

---

### DELETE /dashboard/etudiants/{id}
Supprime un étudiant. *(ADMIN)*

```bash
curl -X DELETE http://localhost:8080/api/dashboard/etudiants/1 \
  -H "Authorization: Bearer <TOKEN>"
```

---

### GET /dashboard/etudiants/search/{keyword}
Recherche d'étudiants.

```bash
curl -X GET http://localhost:8080/api/dashboard/etudiants/search/alaoui \
  -H "Authorization: Bearer <TOKEN>"
```

---

### GET /dashboard/etudiants/filiere/{filiereId}
Récupère les étudiants d'une filière.

```bash
curl -X GET http://localhost:8080/api/dashboard/etudiants/filiere/1 \
  -H "Authorization: Bearer <TOKEN>"
```

---

## 🏢 Entreprises

### GET /dashboard/entreprises
Récupère toutes les entreprises.

```bash
curl -X GET http://localhost:8080/api/dashboard/entreprises \
  -H "Authorization: Bearer <TOKEN>"
```

---

### POST /dashboard/entreprises
Crée une nouvelle entreprise. *(ADMIN/ENCADRANT)*

```bash
curl -X POST http://localhost:8080/api/dashboard/entreprises \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Nouvelle Entreprise",
    "adresse": "123 Rue Principal",
    "telephone": "0123456789",
    "email": "contact@company.fr",
    "ville": "Paris",
    "pays": "France",
    "responsableEmail": "ceo@company.fr",
    "encadrantEntrepriseEmail": "encadrant@company.fr",
    "encadrantEntrepriseTelephone": "0987654321"
  }'
```

---

### GET /dashboard/entreprises/search/{keyword}
Recherche d'entreprises.

```bash
curl -X GET http://localhost:8080/api/dashboard/entreprises/search/tech \
  -H "Authorization: Bearer <TOKEN>"
```

---

## 📚 Filières

### GET /dashboard/filieres
Récupère toutes les filières.

```bash
curl -X GET http://localhost:8080/api/dashboard/filieres \
  -H "Authorization: Bearer <TOKEN>"
```

---

### POST /dashboard/filieres
Crée une nouvelle filière. *(ADMIN)*

```bash
curl -X POST http://localhost:8080/api/dashboard/filieres \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "intitule": "Nouvelle Filière"
  }'
```

---

### PUT /dashboard/filieres/{id}
Modifie une filière. *(ADMIN)*

```bash
curl -X PUT http://localhost:8080/api/dashboard/filieres/1 \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "intitule": "Filière Modifiée"
  }'
```

---

## 👨‍🏫 Encadrants

### GET /dashboard/encadrants
Récupère tous les encadrants.

```bash
curl -X GET http://localhost:8080/api/dashboard/encadrants \
  -H "Authorization: Bearer <TOKEN>"
```

---

### POST /dashboard/encadrants
Crée un nouvel encadrant. *(ADMIN)*

```bash
curl -X POST http://localhost:8080/api/dashboard/encadrants \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Nouveau",
    "departement": "Informatique",
    "etablissement": "Université X",
    "email": "new.encadrant@univ.fr"
  }'
```

---

### GET /dashboard/encadrants/search/{keyword}
Recherche d'encadrants.

```bash
curl -X GET http://localhost:8080/api/dashboard/encadrants/search/martin \
  -H "Authorization: Bearer <TOKEN>"
```

---

## 📋 Codes d'Erreur Courants

| Code | Signification | Cause |
|------|---------------|-------|
| `200` | OK | Requête réussie |
| `201` | Created | Ressource créée |
| `400` | Bad Request | Données invalides |
| `401` | Unauthorized | Token manquant/invalide |
| `403` | Forbidden | Permissions insuffisantes |
| `404` | Not Found | Ressource non trouvée |
| `500` | Server Error | Erreur serveur |

---

## 🔑 Format du Token

Le token JWT doit être envoyé dans le header:
```
Authorization: Bearer <TOKEN_HERE>
```

**Exemple complet:**
```bash
curl -X GET http://localhost:8080/api/dashboard/stages \
  -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBwZmUuY29tIiwiaWF0IjoxNzA2NzA0ODAwLCJleHAiOjE3MDY3OTE2MDB9.xYz..."
```

---

## 🔄 Pagination & Filtres

### Pagination

Les listes supportent la pagination côté frontend (pas de paramètres d'API):
- Page size: 10 items par défaut
- Navigation: Previous/Next buttons

### Filtres

Filtres disponibles côté frontend:
- **Recherche**: Par mot-clé
- **Statut**: Pour les stages
- **Filière**: Pour les étudiants
- **Année**: Pour les stages

---

## 📝 Response Format

Toutes les réponses suivent le format:

```json
{
  "success": boolean,
  "message": "string",
  "data": object|array,
  "timestamp": number
}
```

**Exemples:**

Succès:
```json
{
  "success": true,
  "message": "Stages retrieved successfully",
  "data": [...],
  "timestamp": 1706704800000
}
```

Erreur:
```json
{
  "success": false,
  "message": "Unauthorized",
  "data": null,
  "timestamp": 1706704800000
}
```

---

## 🧪 Test avec Postman/REST Client

### Importer la Collection

Créer un fichier `pfe-api.postman_collection.json`:

```json
{
  "info": {
    "name": "PFE Management API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "auth": {
    "type": "bearer",
    "bearer": [
      {
        "key": "token",
        "value": "{{token}}",
        "type": "string"
      }
    ]
  },
  "item": [
    {
      "name": "Auth",
      "item": [
        {
          "name": "Login",
          "request": {
            "method": "POST",
            "url": "http://localhost:8080/api/auth/login",
            "body": {
              "email": "admin@pfe.com",
              "password": "password"
            }
          }
        }
      ]
    }
  ]
}
```

Importer dans Postman et utiliser!

---

## 🔗 Swagger UI

**Accès automatique:**
```
http://localhost:8080/api/swagger-ui.html
```

Swagger fournit:
- ✅ Documentation interactive
- ✅ Test des endpoints
- ✅ Schémas des réponses
- ✅ Essayer les requêtes en direct

---

**Documentation complète de l'API! 📚**

Pour plus de détails, consulter Swagger UI ou le README.md
