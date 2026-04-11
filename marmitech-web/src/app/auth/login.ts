import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from './login.service';

export const loginGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);

  // Se houver um token guardado, deixa entrar
  if (loginService.getToken()) {
    return true;
  }

  // Se não houver, expulsa para o ecrã de login
  router.navigate(['/login']);
  return false;
};