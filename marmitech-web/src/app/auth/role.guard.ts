import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from './login.service';
import Swal from 'sweetalert2';

export const roleGuard: CanActivateFn = (route, state) => {
    const loginService = inject(LoginService);
    const router = inject(Router);

    // Pega o cargo do usuário logado  ADMIN, CAIXA, COZINHA
    const userRole = loginService.getUsuarioCargo().toUpperCase();

    // Pega as roles esperadas da rota que estao definidas no routes.ts
    const expectedRoles = route.data['roles'] as Array<string>;

    // Se o usuário tiver uma das roles permitidas ele consegue acessar
    if (expectedRoles.includes(userRole)) {
        return true;
    }

    // Se não tiver permissão
    Swal.fire('Acesso Negado', 'Você não tem permissão para acessar esta página.', 'error');
    router.navigate(['/login']);
    return false;
};