# 💻 Configuration Visual Studio Code

Guide pour configurer et lancer l'application dans VSCode.

## 📦 Extensions Recommandées

### Installation des Extensions

1. Ouvrir VSCode
2. Aller à Extensions (Ctrl+Shift+X)
3. Installer les extensions suivantes:

#### Frontend (Angular)
```
Angular Language Service
Angular Snippets
Prettier
ESLint
TypeScript Vue Plugin
Tailwind CSS IntelliSense
```

#### Backend (Spring Boot)
```
Extension Pack for Java
Spring Boot Extension Pack
Spring Initializer
Maven for Java
Debugger for Java
REST Client
```

#### General
```
Git Graph
GitLens
Thunder Client (alternative à Postman)
MySQL
Markdown Preview Enhanced
```

### Installation Rapide

```bash
# Ouvrir le terminal dans VSCode et exécuter:
code --install-extension Angular.ng-template
code --install-extension Angular.ng-template
code --install-extension esbenp.prettier-vscode
code --install-extension dbaeumer.vscode-eslint
code --install-extension bradlc.vscode-tailwindcss
code --install-extension vscjava.extension-pack-for-java
code --install-extension vmware.vscode-spring-boot
code --install-extension humao.rest-client
```

---

## 🗂️ Structurer l'Espace de Travail

### Créer un Multi-Folder Workspace

1. **Fichier > Ajouter un dossier à l'espace de travail**
2. Ajouter `backend/` et `frontend/`

Ou créer `pfe-management.code-workspace`:

```json
{
  "folders": [
    {
      "path": "backend",
      "name": "Spring Boot Backend"
    },
    {
      "path": "frontend",
      "name": "Angular Frontend"
    }
  ],
  "settings": {
    "files.exclude": {
      "**/node_modules": true,
      "**/.gradle": true,
      "**/target": true
    }
  }
}
```

Ouvrir avec: `File > Open Workspace from File`

---

## ⚙️ Configuration .vscode

### Créer `.vscode/settings.json`

```json
{
  // Editor
  "editor.defaultFormatter": "esbenp.prettier-vscode",
  "editor.formatOnSave": true,
  "editor.fontSize": 14,
  "editor.tabSize": 2,
  "editor.insertSpaces": true,
  "editor.trimAutoWhitespace": true,

  // TypeScript
  "typescript.tsdk": "node_modules/typescript/lib",
  "typescript.enablePromptUseWorkspaceTsdk": true,
  "[typescript]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[json]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[html]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },

  // Files
  "files.exclude": {
    "**/node_modules": true,
    "**/.gradle": true,
    "**/target": true,
    "**/.angular": true
  },
  "files.watcherExclude": {
    "**/node_modules/**": true,
    "**/target/**": true,
    "**/.angular/**": true
  },

  // Java
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.import.gradle.wrapper.checksums": "ignore",

  // Search
  "search.exclude": {
    "**/node_modules": true,
    "**/target": true,
    "**/.gradle": true
  }
}
```

### Créer `.vscode/launch.json`

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Spring Boot (Backend)",
      "type": "java",
      "name": "Spring Boot App",
      "request": "launch",
      "cwd": "${workspaceFolder:Spring Boot Backend}",
      "mainClass": "com.pfe.PfeManagementApplication",
      "projectName": "pfe-management",
      "preLaunchTask": "maven: clean",
      "console": "integratedTerminal",
      "internalConsoleOptions": "neverOpen"
    },
    {
      "name": "Angular (Frontend)",
      "type": "chrome",
      "request": "launch",
      "url": "http://localhost:4200",
      "webRoot": "${workspaceFolder:Angular Frontend}",
      "sourceMaps": true
    }
  ]
}
```

### Créer `.vscode/tasks.json`

```json
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Backend: Build",
      "type": "shell",
      "command": "mvn",
      "args": ["clean", "package"],
      "cwd": "${workspaceFolder:Spring Boot Backend}",
      "group": "build",
      "presentation": {
        "echo": true,
        "reveal": "always",
        "panel": "shared"
      }
    },
    {
      "label": "Backend: Run",
      "type": "shell",
      "command": "mvn",
      "args": ["spring-boot:run"],
      "cwd": "${workspaceFolder:Spring Boot Backend}",
      "isBackground": true,
      "group": {
        "kind": "build",
        "isDefault": true
      }
    },
    {
      "label": "Frontend: Install",
      "type": "shell",
      "command": "npm",
      "args": ["install"],
      "cwd": "${workspaceFolder:Angular Frontend}",
      "presentation": {
        "reveal": "silent"
      }
    },
    {
      "label": "Frontend: Start",
      "type": "shell",
      "command": "npm",
      "args": ["start"],
      "cwd": "${workspaceFolder:Angular Frontend}",
      "isBackground": true,
      "group": {
        "kind": "build",
        "isDefault": true
      },
      "presentation": {
        "echo": true,
        "reveal": "always",
        "panel": "shared"
      }
    },
    {
      "label": "Frontend: Build",
      "type": "shell",
      "command": "npm",
      "args": ["run", "build"],
      "cwd": "${workspaceFolder:Angular Frontend}",
      "group": "build"
    }
  ]
}
```

---

## 🚀 Lancer l'Application dans VSCode

### Méthode 1: Terminaux Intégrés (Recommandé)

1. **Terminal 1 - Backend**
   - Ctrl+Shift+` pour ouvrir le terminal
   - Taper: `cd backend && mvn spring-boot:run`

2. **Terminal 2 - Frontend**
   - Ctrl+Shift+` (nouveau terminal)
   - Taper: `cd frontend && npm start`

3. **Application ouvre automatiquement** sur `http://localhost:4200`

### Méthode 2: Tasks VSCode

1. Appuyer sur `Ctrl+Shift+B` (run build task)
2. Sélectionner "Backend: Run"
3. Appuyer à nouveau `Ctrl+Shift+B`
4. Sélectionner "Frontend: Start"

### Méthode 3: Debug Mode

1. **Appuyer F5** pour démarrer le debugging
2. Sélectionner "Spring Boot (Backend)" ou "Angular (Frontend)"
3. Des breakpoints peuvent être mis dans le code

---

## 🔍 Debugging

### Backend (Spring Boot)

#### Ajouter Breakpoint
1. Cliquer à gauche du numéro de ligne
2. Une rouge apparaît
3. Lancer l'app en debug (F5)
4. L'exécution s'arrête au breakpoint

#### Inspection Variables
- Variables locales: Visible dans "Debug" panel
- Stack trace: Visible dans "Call Stack"
- Console: Affiche les logs

#### Exemple Breakpoint
```java
// Placer un breakpoint ici
@PostMapping("/stages")
public ResponseEntity<GenericDTOs.ApiResponse<StagePFEDTO>> createStagePFE(
    @RequestBody StagePFEDTO dto) {  // ← F9 pour placer un breakpoint
    StagePFEDTO stage = stagePFEService.createStagePFE(dto);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(new GenericDTOs.ApiResponse<>("Stage created successfully", stage));
}
```

### Frontend (Angular)

#### Chrome DevTools
1. Appuyer F12 dans le navigateur
2. **Sources** tab pour voir le TypeScript
3. Placer des breakpoints
4. Rafraîchir la page (F5)

#### VSCode Debugger
1. Configuration dans `.vscode/launch.json`
2. Appuyer F5 et sélectionner "Angular (Frontend)"
3. Debugger se connecte au navigateur

---

## 📝 Snippets Utiles

### Spring Boot Service

Créer `pfe-service.json` dans `.vscode/`:

```json
{
  "Spring Boot Service": {
    "prefix": "sbservice",
    "body": [
      "@Service",
      "@RequiredArgsConstructor",
      "@Transactional",
      "public class ${1:ServiceName}Service {",
      "  private final ${1:ServiceName}Repository ${2:repository};",
      "",
      "  public List<${1:ServiceName}DTO> getAll() {",
      "    return ${2:repository}.findAll().stream()",
      "      .map(this::convertToDTO)",
      "      .collect(Collectors.toList());",
      "  }",
      "",
      "  private ${1:ServiceName}DTO convertToDTO(${1:ServiceName} entity) {",
      "    return ${1:ServiceName}DTO.builder()",
      "      // map fields",
      "      .build();",
      "  }",
      "}"
    ],
    "description": "Spring Boot Service Template"
  }
}
```

### Angular Component

```json
{
  "Angular Component": {
    "prefix": "ngcomponent",
    "body": [
      "import { Component, OnInit } from '@angular/core';",
      "import { CommonModule } from '@angular/common';",
      "",
      "@Component({",
      "  selector: 'app-${1:name}',",
      "  standalone: true,",
      "  imports: [CommonModule],",
      "  templateUrl: './${1:name}.component.html',",
      "  styleUrls: ['./${1:name}.component.css']",
      "})",
      "export class ${2:Name}Component implements OnInit {",
      "  constructor() {}",
      "",
      "  ngOnInit(): void {",
      "  }",
      "}",
      ""
    ],
    "description": "Angular Standalone Component"
  }
}
```

---

## 🔗 Ports et URLs

### Accès Locaux

| Service | URL | Port |
|---------|-----|------|
| Frontend | http://localhost:4200 | 4200 |
| Backend | http://localhost:8080 | 8080 |
| API Base | http://localhost:8080/api | 8080 |
| Swagger | http://localhost:8080/api/swagger-ui.html | 8080 |
| MySQL | localhost:3306 | 3306 |

### Tester l'API

Créer `test.http` à la racine:

```http
### Login
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "email": "admin@pfe.com",
  "password": "password"
}

### Get All Stages
GET http://localhost:8080/api/dashboard/stages
Authorization: Bearer <token_from_login>

### Create Stage
POST http://localhost:8080/api/dashboard/stages
Content-Type: application/json
Authorization: Bearer <token>

{
  "sujet": "New Stage",
  "dateDebut": "2024-02-01",
  "dateFin": "2024-05-31",
  "description": "Stage description",
  "objectifs": "Stage objectives",
  "solution": "Solution proposed",
  "anneeUniversitaire": "2023-2024",
  "statut": "PROPOSE",
  "entrepriseId": 1,
  "encadrantId": 1
}
```

Avec l'extension **REST Client**, Ctrl+Click sur "Send Request"

---

## 🎯 Workflow Recommandé

### Matin - Configuration
1. Ouvrir VSCode
2. Ouvrir workspace: `pfe-management.code-workspace`
3. Ouvrir 2 terminaux intégrés
4. Lancer backend dans terminal 1
5. Lancer frontend dans terminal 2
6. Attendre les messages "Compiled successfully"

### Développement
1. Modifier le code
2. Hot reload automatique (frontend et backend)
3. Utiliser F12 pour DevTools (frontend)
4. Utiliser Debug Panel pour Java (backend)
5. Vérifier les logs dans les terminaux

### Commit
1. Ouvrir Source Control (Ctrl+Shift+G)
2. Vérifier les changements
3. Écrire le message
4. Commit & Push

---

## 🐛 Troubleshooting VSCode

### Extension Java ne fonctionne pas
```bash
# Réinstaller
code --uninstall-extension redhat.java
code --install-extension redhat.java
# Redémarrer VSCode
```

### Compilateur TypeScript ne s'engage pas
```bash
# Dans le dossier frontend:
npm install -g typescript
npm install typescript
```

### Port déjà utilisé
```bash
# Trouver le process
netstat -ano | findstr :8080  # Windows
lsof -i :8080                 # Mac/Linux

# Tuer le process
taskkill /PID <PID> /F        # Windows
kill -9 <PID>                 # Mac/Linux
```

### IntelliSense ne fonctionne pas
```
Ctrl+Shift+P > Developer: Reload Window
```

---

## 📚 Resources VSCode

- **Docs**: https://code.visualstudio.com/docs
- **Keybindings**: Ctrl+K Ctrl+S
- **Settings**: Ctrl+,
- **Command Palette**: Ctrl+Shift+P

---

## ✨ Pro Tips

1. **Formatage Auto**: Ctrl+Shift+P > Format Document
2. **Renommage Multi-fichiers**: Avec Ctrl+H (Find & Replace)
3. **Folding**: Ctrl+K Ctrl+0/J pour fold/unfold
4. **Zen Mode**: Ctrl+K Z pour focus
5. **Split Editor**: Ctrl+\ ou Ctrl+K Ctrl+\

---

**Bon développement dans VSCode! 💻**
