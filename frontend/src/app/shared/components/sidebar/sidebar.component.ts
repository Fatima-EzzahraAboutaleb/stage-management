import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AuthService } from '../../../core/services/auth.service';
import { CurrentUser } from '../../../core/models/auth.model';
import { LucideAngularModule } from 'lucide-angular';

interface NavItem {
  label: string;
  route: string;
  icon: string;
  roles?: string[];
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, LucideAngularModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit, OnDestroy {
  isOpen = true;
  currentUser: CurrentUser | null = null;
  private destroy$ = new Subject<void>();

  navItems: NavItem[] = [
    { label: 'Tableau de bord', route: 'home', icon: 'layout-grid', roles: ['ADMIN', 'ENCADRANT', 'ETUDIANT'] },
    { label: 'Stages', route: 'stages', icon: 'briefcase', roles: ['ADMIN', 'ENCADRANT', 'ETUDIANT'] },
    { label: 'Étudiants', route: 'etudiants', icon: 'users', roles: ['ADMIN', 'ENCADRANT'] },
    { label: 'Entreprises', route: 'entreprises', icon: 'building-2', roles: ['ADMIN', 'ENCADRANT'] },
    { label: 'Filières', route: 'filieres', icon: 'book-open', roles: ['ADMIN'] },
    { label: 'Encadrants', route: 'encadrants', icon: 'user-check', roles: ['ADMIN'] },
    { label: 'Profil', route: 'profil', icon: 'user', roles: ['ADMIN', 'ENCADRANT', 'ETUDIANT'] },
    { label: 'Paramètres', route: 'parametres', icon: 'settings', roles: ['ADMIN', 'ENCADRANT', 'ETUDIANT'] }
  ];

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.currentUser$
      .pipe(takeUntil(this.destroy$))
      .subscribe(user => {
        this.currentUser = user;
      });
  }

  ngOnDestroy(): void {
    this.destroy$.complete();
  }

  toggleSidebar(): void {
    this.isOpen = !this.isOpen;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isNavItemVisible(navItem: NavItem): boolean {
    if (!navItem.roles || navItem.roles.length === 0) {
      return true;
    }
    return navItem.roles.includes(this.currentUser?.role || '');
  }
}
