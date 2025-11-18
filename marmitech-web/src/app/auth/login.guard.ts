import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { LoginService } from './login.service';

export const loginGuard: CanActivateFn = (_route, state) => {
  
 let loginService = inject(LoginService);

 if(loginService.hasPermission('Cozinha')  && state.url == '/admin/produtos')
  {
    alert("Voce nao tem permissao para acessar essa pagina");
    return false;
 }

 if((loginService.hasPermission('Caixa') ||loginService.hasPermission('Cozinha')) && state.url == '/admin/usuarios')
  {
    alert("Voce nao tem permissao para acessar essa pagina");
    return false;
 }
 if((loginService.hasPermission('Caixa') || loginService.hasPermission('Cozinha')) && state.url == '/admin/historico'){
   alert("Voce nao tem permissao para acessar essa pagina");
   return false;
 }
if((loginService.hasPermission('Caixa') || loginService.hasPermission('Cozinha') ) && state.url == '/admin/relatorio'){
  alert("Voce nao tem permissao para acessar essa pagina");
  return false;
}


    return true;
  }
  