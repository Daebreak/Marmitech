import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from './login.service';
import Swal from 'sweetalert2';

export const roleGuard: CanActivateFn = (route, state) => {
    const loginService = inject(LoginService);
    const router = inject(Router);

    // Pega o cargo do usuário logado (ex: CAIXA, ADMIN, COZINHA)
    const userRole = loginService.getUsuarioCargo();

    // Pega as roles esperadas da rota (ex: ['ADMIN', 'CAIXA' , 'COZINHA'])
    const expectedRoles = route.data['roles'] as Array<string>;

    // Verifica se o cargo do usuário bate com alguma das permitidas
    // Aceita variações como ADMIN ou ROLE_ADMIN
    const hasPermission = expectedRoles.some(role =>
        userRole === role.toUpperCase() || userRole === 'ROLE_' + role.toUpperCase()
    );

    if (hasPermission) {
        return true;
    }

    // Se não tiver permissão
    Swal.fire('Acesso Negado', 'Você não tem permissão para acessar esta página.', 'error');
    router.navigate(['/login']);
    return false;
};
