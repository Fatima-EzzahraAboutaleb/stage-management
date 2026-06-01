import { Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: 'register',
    loadComponent: () => import('./features/auth/register/register.component').then(m => m.RegisterComponent)
  },
  {
    path: 'dashboard',
    canActivate: [AuthGuard],
    loadComponent: () => import('./layout/main-layout/main-layout.component').then(m => m.MainLayoutComponent),
    children: [
      {
        path: 'home',
        loadComponent: () => import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent)
      },
      {
        path: 'stages',
        loadComponent: () => import('./features/stages/stages-list/stages-list.component').then(m => m.StagesListComponent)
      },
      {
        path: 'stages/:id',
        loadComponent: () => import('./features/stages/stage-detail/stage-detail.component').then(m => m.StageDetailComponent)
      },
      {
        path: 'etudiants',
        loadComponent: () => import('./features/etudiants/etudiants-list/etudiants-list.component').then(m => m.EtudientsListComponent)
      },
      {
        path: 'entreprises',
        loadComponent: () => import('./features/entreprises/entreprises-list/entreprises-list.component').then(m => m.EntreprisesListComponent)
      },
      {
        path: 'filieres',
        loadComponent: () => import('./features/filieres/filieres-list/filieres-list.component').then(m => m.FilieresListComponent)
      },
      {
        path: 'encadrants',
        loadComponent: () => import('./features/encadrants/encadrants-list/encadrants-list.component').then(m => m.EncadrantsListComponent)
      },
      {
        path: 'profil',
        loadComponent: () => import('./features/profile/profile.component').then(m => m.ProfileComponent)
      },
      {
        path: 'parametres',
        loadComponent: () => import('./features/settings/settings.component').then(m => m.SettingsComponent)
      },
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: '**',
    loadComponent: () => import('./features/not-found/not-found.component').then(m => m.NotFoundComponent)
  }
];
