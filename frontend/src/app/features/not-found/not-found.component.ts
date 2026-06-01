import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { LucideAngularModule } from 'lucide-angular';

@Component({
  selector: 'app-not-found',
  standalone: true,
  imports: [CommonModule, RouterLink, LucideAngularModule],
  template: `
    <div class="min-h-screen flex items-center justify-center bg-background">
      <div class="text-center">
        <i-lucide name="inbox" class="w-24 h-24 text-gray-300 dark:text-gray-600 mx-auto mb-6"></i-lucide>
        <h1 class="text-6xl font-bold text-primary dark:text-secondary mb-2">404</h1>
        <p class="text-2xl font-semibold text-gray-600 dark:text-gray-400 mb-4">Page introuvable</p>
        <p class="text-gray-500 dark:text-gray-500 mb-8">La page que vous cherchez n&apos;existe pas.</p>
        <a routerLink="/dashboard" class="btn btn-primary">
          Retourner au tableau de bord
        </a>
      </div>
    </div>
  `
})
export class NotFoundComponent {}
