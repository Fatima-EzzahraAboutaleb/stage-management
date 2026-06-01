# ✅ Checklist d'Installation - PFE Management System

Suivez cette checklist pour installer et lancer l'application correctement.

---

## Phase 1: Prérequis (10 min)

- [ ] **Java 17+** installé
  - Vérifier: `java -version`
  - Télécharger: https://www.oracle.com/java/technologies/downloads/

- [ ] **Node.js 18+** installé
  - Vérifier: `node -v`
  - Télécharger: https://nodejs.org/

- [ ] **pnpm** installé (ou npm)
  - Installer: `npm install -g pnpm`
  - Vérifier: `pnpm -v`

- [ ] **MySQL 8.0+** installé et lancé
  - Vérifier: `mysql --version`
  - Service actif: `mysql -u root -p -e "SELECT 1"`

- [ ] **Git** installé (optionnel)
  - Vérifier: `git --version`

- [ ] **VSCode** ou IDE installé (optionnel)

---

## Phase 2: Configuration MySQL (5 min)

### Étape 1: Créer la Base de Données

```bash
# Connexion à MySQL
mysql -u root -p

# Dans le prompt MySQL:
CREATE DATABASE pfe_management;
EXIT;
```

**Vérifier:**
```bash
mysql -u root -p -e "SHOW DATABASES LIKE 'pfe_management';"
```

### Étape 2: Importer les Données

```bash
# À partir du répertoire racine du projet:
cd backend
mysql -u root -p pfe_management < src/main/resources/init.sql
```

**Vérifier:**
```bash
mysql -u root -p pfe_management -e "SHOW TABLES;"
```

✅ Doit afficher 8 tables

### Étape 3: Adapter la Configuration

Éditer `backend/src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pfe_management
    username: root                    # ← Adapter si différent
    password: root                    # ← Adapter si différent
    driver-class-name: com.mysql.cj.jdbc.Driver
```

---

## Phase 3: Backend Spring Boot (5 min)

### Étape 1: Accéder au Répertoire

```bash
cd backend
```

### Étape 2: Télécharger les Dépendances

```bash
# Première fois uniquement:
mvn clean install
```

**Attendre:** "BUILD SUCCESS"

### Étape 3: Lancer le Serveur

```bash
mvn spring-boot:run
```

**Attendre:** Message "Started PfeManagementApplication"

**Vérifier:**
```bash
curl http://localhost:8080/api/swagger-ui.html
```

✅ Le Swagger UI doit s'ouvrir

### Ouvrir Nouveau Terminal

Garder ce terminal ouvert, ouvrir un nouveau terminal pour le frontend.

---

## Phase 4: Frontend Angular (5 min)

### Étape 1: Accéder au Répertoire

```bash
cd frontend
```

### Étape 2: Installer les Dépendances

```bash
# Première fois uniquement:
pnpm install
```

**Attendre:** "added X packages"

### Étape 3: Démarrer le Serveur

```bash
npm start
```

**Attendre:** "Application bundle generated successfully"

**Automatiquement:** Le navigateur ouvre http://localhost:4200

---

## Phase 5: Vérification (2 min)

### Backend
- [ ] Swagger UI: http://localhost:8080/api/swagger-ui.html - OK
- [ ] Terminal backend: "Started PfeManagementApplication" - OK
- [ ] Pas d'erreurs en rouge - OK

### Frontend
- [ ] Application accessible: http://localhost:4200 - OK
- [ ] Login page affichée - OK
- [ ] Terminal frontend: "Application bundle generated" - OK

### Navigateur
- [ ] Application chargée - OK
- [ ] Pas d'erreurs console (F12) - OK

---

## Phase 6: Test de Connexion (2 min)

### Connexion

Utiliser ces identifiants:

```
Email: admin@pfe.com
Password: password
```

### Vérifier Après Connexion

- [ ] Redirection vers /dashboard - OK
- [ ] Dashboard chargé avec statistiques - OK
- [ ] Menu sidebar visible - OK
- [ ] Navigation fonctionnelle - OK

### Test Rapide

1. Cliquer sur "Stages" dans le menu
2. Voir la liste des stages
3. Cliquer sur "Étudiants"
4. Voir la liste des étudiants
5. Cliquer sur "Tableau de bord"
6. Revenir au dashboard

✅ Si tout fonctionne, l'installation est réussie!

---

## Phase 7: Arrêt Sécurisé

### Arrêter les Serveurs

**Terminal Backend:**
```
Ctrl + C
```

**Terminal Frontend:**
```
Ctrl + C
```

### Vérifier l'Arrêt
```bash
# Vérifier que les ports sont libres:
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Mac/Linux
```

✅ Aucun résultat = arrêt correct

---

## Démarrage Suivant (Rapide)

Pour relancer l'application après un redémarrage:

### Terminal 1:
```bash
cd backend && mvn spring-boot:run
```

### Terminal 2:
```bash
cd frontend && npm start
```

**Attendre:**
- Backend: "Started PfeManagementApplication"
- Frontend: "Application bundle generated successfully"

---

## Troubleshooting Rapide

### Port 8080 déjà utilisé
```bash
# Trouver le process
netstat -ano | findstr :8080
# Tuer le process
taskkill /PID <PID> /F
```

### Port 4200 déjà utilisé
```bash
cd frontend && npm start -- --port 4300
```

### MySQL erreur de connexion
```bash
# Vérifier que MySQL est lancé
mysql -u root -p -e "SELECT 1"
# Redémarrer le service si nécessaire
```

### Application ne charge pas
1. Vérifier la console browser (F12)
2. Vérifier les logs terminal
3. Redémarrer les serveurs
4. Vider le cache navigateur (Ctrl+Shift+Delete)

### "Cannot find module" en frontend
```bash
cd frontend
rm -rf node_modules
pnpm install
npm start
```

---

## Points de Vérification

### Données de Base
- [ ] 3 Users (admin, encadrant, étudiant)
- [ ] 3 Filières
- [ ] 2 Entreprises
- [ ] 2 Encadrants
- [ ] 3 Étudiants
- [ ] 2 Stages

**Vérifier dans:**
- Dashboard: voir les chiffres
- MySQL: `SELECT COUNT(*) FROM <table>;`

### Fonctionnalités Clés
- [ ] Login/Logout fonctionne
- [ ] Dark mode toggle fonctionne
- [ ] Navigation sidebar fonctionne
- [ ] Recherche fonctionne
- [ ] Pagination fonctionne
- [ ] Filtres fonctionnent
- [ ] Formulaires valident
- [ ] Notifications s'affichent

---

## Prochaines Étapes

### Exploration (30 min)
1. Tester tous les rôles (Admin/Encadrant/Étudiant)
2. Tester toutes les pages
3. Tester les formulaires
4. Tester les filters/recherche

### Développement (si needed)
1. Lire README.md pour documentation complète
2. Consulter DATABASE.md pour la base de données
3. Modifier le code pour ajouter des features
4. Tester les changements

### Déploiement (optionnel)
1. Lire le README.md section Déploiement
2. Préparer l'environnement de production
3. Configurer les variables d'environnement
4. Déployer sur serveur

---

## Support

### Documentation
- **README.md** (523 lignes) - Guide complet
- **QUICKSTART.md** (309 lignes) - Démarrage rapide
- **DATABASE.md** (445 lignes) - Base de données
- **PROJECT_SUMMARY.md** (510 lignes) - Vue d'ensemble
- **VSCODE_SETUP.md** (519 lignes) - VSCode configuration

### Ressources
- Spring Boot: https://spring.io
- Angular: https://angular.io
- MySQL: https://dev.mysql.com
- Tailwind: https://tailwindcss.com

---

## Status d'Installation

Une fois cette checklist complétée:

| Élément | Status |
|---------|--------|
| Backend | ✅ Prêt |
| Frontend | ✅ Prêt |
| Database | ✅ Prêt |
| Authentification | ✅ Testée |
| Dashboard | ✅ Fonctionnel |
| Navigation | ✅ Fonctionnelle |
| **Statut Global** | **✅ COMPLET** |

---

## Félicitations! 🎉

L'application PFE Management est maintenant installée et prête à l'emploi!

**Prochaines actions:**
1. Explorer l'interface
2. Tester les fonctionnalités
3. Lire la documentation
4. Personnaliser si nécessaire
5. Déployer en production

---

**Questions? Consultez le README.md ou le QUICKSTART.md!**

**Bon développement! 🚀**
