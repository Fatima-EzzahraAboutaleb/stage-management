import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../../core/services/auth.service';
import { LucideAngularModule } from 'lucide-angular';
import { RegisterRequest } from '../../../core/models/auth.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, LucideAngularModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  email = '';
  password = '';
  confirmPassword = '';
  nom = '';
  prenom = '';
  role = 'ETUDIANT';
  isLoading = false;
  showPassword = false;

  roles = ['ADMIN', 'ENCADRANT', 'ETUDIANT'];

  constructor(
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/dashboard']);
    }
  }

  handleRegister(): void {
    // Validation
    if (!this.email || !this.password || !this.nom || !this.prenom) {
      this.toastr.error('Veuillez remplir tous les champs');
      return;
    }

    if (!this.isValidEmail(this.email)) {
      this.toastr.error('Email invalide');
      return;
    }

    if (this.password.length < 6) {
      this.toastr.error('Le mot de passe doit contenir au moins 6 caractères');
      return;
    }

    if (this.password !== this.confirmPassword) {
      this.toastr.error('Les mots de passe ne correspondent pas');
      return;
    }

    this.isLoading = true;

    const registerRequest: RegisterRequest = {
      email: this.email,
      password: this.password,
      nom: this.nom,
      prenom: this.prenom,
      role: this.role
    };

    this.authService.register(registerRequest).subscribe({
      next: (response) => {
        this.toastr.success('Inscription réussie!');
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Register error:', error);
        this.toastr.error(error.error?.message || 'Erreur lors de l\'inscription');
        this.isLoading = false;
      }
    });
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  private isValidEmail(email: string): boolean {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
}
