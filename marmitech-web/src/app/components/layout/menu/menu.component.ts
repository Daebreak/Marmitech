import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { LoginService } from '../../../auth/login.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink, MdbCollapseModule, MdbDropdownModule, CommonModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {

  router = inject(Router);
  loginService = inject(LoginService);

  get isAdmin() {
    return this.loginService.hasRole('ADMIN');
  }

  get isCaixa() {
    return this.loginService.hasRole('CAIXA');
  }

  get isCozinha() {
    return this.loginService.hasRole('COZINHA');
  }

  logout() {
    this.loginService.removerToken();
    // Redireciona o usuário para a tela de login
    this.router.navigate(['/login']);
  }
}