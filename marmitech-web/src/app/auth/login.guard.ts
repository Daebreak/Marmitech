import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { LoginService } from './login.service';

export const loginGuard: CanActivateFn = (_route, state) => {
  
 let loginService = inject(LoginService);

 if(loginService.hasPermission('admin') && state.url == '/admin/produtos')
  {
    alert("Voce nao tem permissao para acessar essa pagina");
    return true;
 }
  return false;
};