import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { LoginService } from '../../../auth/login.service';
import { NgIf } from '@angular/common';



@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [RouterLink, MdbCollapseModule, MdbDropdownModule, NgIf],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
   loginService = inject(LoginService);
}
