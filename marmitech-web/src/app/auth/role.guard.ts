import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { KeycloakService } from './login.service'; // Certifique-se de que o caminho e o nome estão corretos

export const roleGuard: CanActivateFn = (route, state) => {
  const keycloakService = inject(KeycloakService);
  const router = inject(Router);

  // 1. Verifica se o usuário está logado
  if (!keycloakService.isAuthenticated()) {
    keycloakService.logout(); // Força o logout se tentar burlar
    return false;
  }

  // 2. Pega as roles exigidas pela rota (definidas lá no app.routes.ts)
  const rolesExigidas = route.data['roles'] as Array<string>;

  // Se a rota não exige nenhuma role específica, deixa passar
  if (!rolesExigidas || rolesExigidas.length === 0) {
    return true;
  }

  // 3. Verifica se o usuário tem pelo menos UMA das roles exigidas
  const temPermissao = rolesExigidas.some(role => keycloakService.hasRole(role));

  if (temPermissao) {
    return true; // Deixa carregar a tela
  } else {
    // Se não tiver permissão, redireciona para uma tela segura ou avisa
    console.warn('Acesso Negado: Você não tem as roles necessárias:', rolesExigidas);
    alert('Acesso Negado! Você não tem permissão para acessar esta tela.');
    return false; // A tela fica em branco ou parada se retornar false sem redirecionar
  }
};