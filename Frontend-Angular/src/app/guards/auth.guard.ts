import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const token = authService.getToken();
  if (token) {
    // Vérifiez la validité du token si nécessaire (e.g., expiration)
    return true;
  } else {
    // Redirigez l'utilisateur vers la page de connexion s'il n'est pas authentifié
    router.navigate(['/login']);
    return false;
  }
};
