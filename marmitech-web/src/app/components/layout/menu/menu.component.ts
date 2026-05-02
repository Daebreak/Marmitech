import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { KeycloakService } from '../../../auth/login.service';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink, MdbCollapseModule, MdbDropdownModule, CommonModule],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {

  loginService = inject(KeycloakService);

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
    this.loginService.logout();
  }
}