# ⚡ Guide de Démarrage Rapide

Lancez l'application PFE Management en **5 minutes** !

## Étape 1 : Configuration MySQL

```bash
# 1. Créer la base de données
mysql -u root -p

# Dans le prompt MySQL:
CREATE DATABASE pfe_management;
EXIT;

# 2. Importer les données initiales
mysql -u root -p pfe_management < backend/src/main/resources/init.sql
```

**Note:** Le mot de passe par défaut est `root`. Changez-le dans `backend/src/main/resources/application.yml` si différent.

---

## Étape 2 : Démarrer le Backend

```bash
# Ouvrir un terminal 1
cd backend

# Démarrer Spring Boot
mvn spring-boot:run

# ✓ Le serveur démarre sur http://localhost:8080
# ✓ Swagger UI: http://localhost:8080/api/swagger-ui.html
```

**Vérifier:** Ouvrir http://localhost:8080/api/swagger-ui.html dans le navigateur

---

## Étape 3 : Démarrer le Frontend

```bash
# Ouvrir un terminal 2
cd frontend

# Installer les dépendances (première fois)
pnpm install

# Démarrer le serveur Angular
npm start

# ✓ L'application démarre sur http://localhost:4200
# ✓ Ouverture automatique du navigateur
```

---

## Étape 4 : Connexion

Dans l'application qui s'ouvre automatiquement:

**Utiliser ces identifiants de démo:**

```
Email: admin@pfe.com
Mot de passe: password
```

Autres comptes disponibles:
- encadrant@pfe.com / password
- etudiant@pfe.com / password

---

## 🎯 Que faire ensuite?

Une fois connecté, vous pouvez:

1. **Consulter le Dashboard** - Statistiques et graphiques
2. **Créer des Stages** - Menu "Nouveau Stage"
3. **Ajouter des Étudiants** - Section Étudiants
4. **Parcourir les Entreprises** - Voir les partenaires
5. **Explorer les Rôles** - Admin/Encadrant/Étudiant

---

## 🔧 Troubleshooting Rapide

### Backend ne démarre pas
```bash
# Vérifier que MySQL est lancé
mysql -u root -p -e "SELECT 1"

# Vérifier le port 8080
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Mac/Linux
```

### Frontend ne démarre pas
```bash
# Nettoyer les dépendances
rm -rf node_modules
pnpm install

# Relancer
npm start
```

### Erreur CORS
- Vérifier que le backend est sur http://localhost:8080
- Vérifier que le frontend est sur http://localhost:4200
- Relancer les deux serveurs

### Oublié les identifiants?
```sql
-- Dans MySQL, rechercher les users:
USE pfe_management;
SELECT * FROM users;
```

---

## 📁 Structure des Fichiers Clés

```
projet/
├── backend/
│   ├── pom.xml              ← Dépendances Maven
│   ├── src/main/resources/
│   │   ├── application.yml  ← Config MySQL
│   │   └── init.sql         ← Données initiales
│   └── src/main/java/com/pfe/
│       ├── entity/          ← Modèles de données
│       ├── service/         ← Logique métier
│       └── controller/      ← API REST
│
├── frontend/
│   ├── package.json         ← Dépendances npm
│   ├── tailwind.config.js   ← Thème personnalisé
│   └── src/
│       ├── app/
│       │   ├── core/        ← Services et guards
│       │   ├── features/    ← Pages
│       │   └── shared/      ← Composants partagés
│       └── styles.css       ← Styles globaux
│
└── README.md                ← Documentation complète
```

---

## 🚀 Commandes Utiles

### Backend

```bash
cd backend

# Démarrer
mvn spring-boot:run

# Compiler
mvn clean package

# Nettoyer
mvn clean

# Tests
mvn test
```

### Frontend

```bash
cd frontend

# Démarrer (dev)
npm start

# Builder (prod)
npm run build

# Tests
npm test

# Linting
ng lint
```

---

## 💾 Données de Test

L'application est livrée avec des données de test pré-chargées:

**3 Users:**
- 1 Admin
- 1 Encadrant
- 1 Étudiant

**3 Filières:**
- Informatique
- Génie Logiciel
- Réseaux et Sécurité

**2 Entreprises:**
- TechCorp (Paris)
- DataSystems (Lyon)

**2 Encadrants:**
- Martin (Université X)
- Legrand (Université X)

**3 Étudiants:**
- Alaoui Mohammed
- Benali Fatima
- Cherif Ahmed

**2 Stages:**
- Stage en cours chez TechCorp
- Stage proposé chez DataSystems

---

## 🎨 Personnalisation

### Changer les couleurs

Éditer `frontend/src/styles.css`:
```css
:root {
  --primary: #0f172a;        /* Bleu Marin */
  --secondary: #ffffff;      /* Blanc */
  --accent: #3b82f6;         /* Bleu Ciel */
  /* ... autres couleurs ... */
}
```

### Changer le nom de l'application

**Backend:**
- Éditer `application.yml`
- Éditer les titres dans les controllers

**Frontend:**
- Éditer `src/app/shared/components/sidebar/sidebar.component.html`
- Éditer `src/index.html` (title)

---

## 📱 Responsive Design

L'app est entièrement responsive:
- **Desktop** (> 1024px) - Tout visible
- **Tablet** (768-1024px) - Layout adapté
- **Mobile** (< 768px) - Navigation par menu

Testez avec F12 > Toggle Device Toolbar

---

## 🔒 Sécurité (Dev)

**En développement:**
- JWT valide 24h (changer en prod!)
- CORS ouvert (à restreindre en prod!)
- Mot de passe de test visible (changer!)

**Pour la production:**
- Générer une clé JWT sécurisée
- Restreindre les CORS origins
- Utiliser HTTPS
- Hashé des mots de passe (BCrypt)

---

## 📚 Prochaines Étapes

1. **Explorez le Dashboard** - Comprendre l'UI
2. **Testez les CRUD** - Créer/modifier/supprimer
3. **Consultez Swagger** - Explorer l'API complète
4. **Lisez le README.md** - Documentation détaillée
5. **Modifiez le code** - Ajouter vos propres features!

---

## ❓ Questions Fréquentes

**Q: Je dois changer le port?**
A: Backend: `application.yml`, Frontend: `npm start -- --port 4300`

**Q: Comment arrêter les serveurs?**
A: Ctrl+C dans les terminaux

**Q: Les données persistent?**
A: Oui, dans MySQL. Supprimer tout: `DROP DATABASE pfe_management;`

**Q: Comment modifier les données de test?**
A: Éditer `init.sql` et relancer MySQL

**Q: Ajouter une nouvelle page?**
A: Créer un component en `features/`, ajouter la route dans `app.routes.ts`

---

**Besoin d'aide? Consultez le README.md complet pour la documentation détaillée!**

Bon développement! 🚀
