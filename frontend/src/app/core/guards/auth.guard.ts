import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    const isLoggedIn = this.authService.isLoggedIn();

    if (isLoggedIn) {
      // Check if route requires specific roles
      if (route.data['roles'] && route.data['roles'].length > 0) {
        const userRole = this.authService.getCurrentUser()?.role;
        if (userRole && route.data['roles'].includes(userRole)) {
          return true;
        }
        // User doesn't have required role
        this.router.navigate(['/dashboard']);
        return false;
      }
      return true;
    }

    // Store the attempted URL for redirecting after login
    localStorage.setItem('redirectUrl', state.url);
    this.router.navigate(['/login']);
    return false;
  }
}
