import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LucideAngularModule } from 'lucide-angular';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../core/services/auth.service';

// Profile Component
@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  template: `
    <div class="space-y-6 max-w-2xl animate-fade-in">
      <h1 class="text-3xl font-bold text-primary dark:text-secondary">Mon Profil</h1>
      
      <div class="card p-8">
        <div class="flex items-center gap-6 mb-8">
          <div class="w-24 h-24 rounded-full bg-gradient-to-br from-accent to-accent-light flex items-center justify-center text-white text-4xl font-bold">
            {{ (currentUser?.prenom?.charAt(0) || 'U') }}
          </div>
          <div>
            <h2 class="text-2xl font-bold text-primary dark:text-secondary">{{ currentUser?.nom }} {{ currentUser?.prenom }}</h2>
            <p class="text-gray-600 dark:text-gray-400">{{ currentUser?.email }}</p>
            <span class="badge badge-info mt-2">{{ currentUser?.role }}</span>
          </div>
        </div>

        <div class="space-y-6">
          <div class="grid grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-primary dark:text-secondary mb-2">Prénom</label>
              <p class="text-gray-600 dark:text-gray-400">{{ currentUser?.prenom }}</p>
            </div>
            <div>
              <label class="block text-sm font-medium text-primary dark:text-secondary mb-2">Nom</label>
              <p class="text-gray-600 dark:text-gray-400">{{ currentUser?.nom }}</p>
            </div>
          </div>

          <div>
            <label class="block text-sm font-medium text-primary dark:text-secondary mb-2">Email</label>
            <p class="text-gray-600 dark:text-gray-400">{{ currentUser?.email }}</p>
          </div>

          <div>
            <label class="block text-sm font-medium text-primary dark:text-secondary mb-2">Rôle</label>
            <p class="text-gray-600 dark:text-gray-400">{{ currentUser?.role }}</p>
          </div>
        </div>
      </div>
    </div>
  `
})
export class ProfileComponent {
  currentUser = this.authService.getCurrentUser();

  constructor(private authService: AuthService) {}
}

// Settings Component
@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [CommonModule, FormsModule, LucideAngularModule],
  template: `
    <div class="space-y-6 max-w-2xl animate-fade-in">
      <h1 class="text-3xl font-bold text-primary dark:text-secondary">Paramètres</h1>
      
      <div class="card p-8">
        <h2 class="text-xl font-bold text-primary dark:text-secondary mb-6">Préférences</h2>
        
        <div class="space-y-4">
          <div class="flex items-center justify-between p-4 border border-gray-200 dark:border-gray-700 rounded-lg">
            <div>
              <p class="font-medium text-primary dark:text-secondary">Mode sombre</p>
              <p class="text-sm text-gray-600 dark:text-gray-400">Activer le thème sombre</p>
            </div>
            <div class="w-12 h-6 bg-gray-300 rounded-full cursor-pointer"></div>
          </div>

          <div class="flex items-center justify-between p-4 border border-gray-200 dark:border-gray-700 rounded-lg">
            <div>
              <p class="font-medium text-primary dark:text-secondary">Notifications</p>
              <p class="text-sm text-gray-600 dark:text-gray-400">Recevoir les notifications par email</p>
            </div>
            <div class="w-12 h-6 bg-accent rounded-full cursor-pointer"></div>
          </div>
        </div>
      </div>

      <div class="card p-8 border-red-200 dark:border-red-900">
        <h2 class="text-xl font-bold text-danger mb-4">Danger Zone</h2>
        <button class="btn bg-red-600 hover:bg-red-700 text-white">
          Supprimer mon compte
        </button>
      </div>
    </div>
  `
})
export class SettingsComponent {}

// Stage Detail Component
@Component({
  selector: 'app-stage-detail',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  template: `
    <div class="space-y-6 animate-fade-in">
      <h1 class="text-3xl font-bold text-primary dark:text-secondary">Détails du Stage</h1>
      
      <div class="card p-8">
        <p class="text-gray-600 dark:text-gray-400">Détails complets du stage à venir...</p>
      </div>
    </div>
  `
})
export class StageDetailComponent {}

// Generic CRUD List Components
@Component({
  selector: 'app-etudiants-list',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  template: `
    <div class="space-y-6 animate-fade-in">
      <div class="flex items-center justify-between">
        <h1 class="text-3xl font-bold text-primary dark:text-secondary">Étudiants</h1>
        <button class="btn btn-primary">
          <i-lucide name="plus" class="w-5 h-5"></i-lucide>
          Nouvel Étudiant
        </button>
      </div>
      <div class="card p-8 text-center">
        <p class="text-gray-600 dark:text-gray-400">Liste des étudiants à venir...</p>
      </div>
    </div>
  `
})
export class EtudientsListComponent {}

@Component({
  selector: 'app-entreprises-list',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  template: `
    <div class="space-y-6 animate-fade-in">
      <div class="flex items-center justify-between">
        <h1 class="text-3xl font-bold text-primary dark:text-secondary">Entreprises</h1>
        <button class="btn btn-primary">
          <i-lucide name="plus" class="w-5 h-5"></i-lucide>
          Nouvelle Entreprise
        </button>
      </div>
      <div class="card p-8 text-center">
        <p class="text-gray-600 dark:text-gray-400">Liste des entreprises à venir...</p>
      </div>
    </div>
  `
})
export class EntreprisesListComponent {}

@Component({
  selector: 'app-filieres-list',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  template: `
    <div class="space-y-6 animate-fade-in">
      <div class="flex items-center justify-between">
        <h1 class="text-3xl font-bold text-primary dark:text-secondary">Filières</h1>
        <button class="btn btn-primary">
          <i-lucide name="plus" class="w-5 h-5"></i-lucide>
          Nouvelle Filière
        </button>
      </div>
      <div class="card p-8 text-center">
        <p class="text-gray-600 dark:text-gray-400">Liste des filières à venir...</p>
      </div>
    </div>
  `
})
export class FilieresListComponent {}

@Component({
  selector: 'app-encadrants-list',
  standalone: true,
  imports: [CommonModule, LucideAngularModule],
  template: `
    <div class="space-y-6 animate-fade-in">
      <div class="flex items-center justify-between">
        <h1 class="text-3xl font-bold text-primary dark:text-secondary">Encadrants</h1>
        <button class="btn btn-primary">
          <i-lucide name="plus" class="w-5 h-5"></i-lucide>
          Nouvel Encadrant
        </button>
      </div>
      <div class="card p-8 text-center">
        <p class="text-gray-600 dark:text-gray-400">Liste des encadrants à venir...</p>
      </div>
    </div>
  `
})
export class EncadrantsListComponent {}

// Stage Form Component (Placeholder)
@Component({
  selector: 'app-stage-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div>
      <p class="text-gray-600 dark:text-gray-400">Formulaire de création/modification de stage à venir...</p>
    </div>
  `
})
export class StageFormComponent {}
