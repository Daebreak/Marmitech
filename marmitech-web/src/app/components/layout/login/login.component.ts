import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { LoginService } from '../../../auth/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginData = { username: '', password: '' };

  router = inject(Router);
  loginService = inject(LoginService);

  logar() {
    this.loginService.logar(this.loginData.username, this.loginData.password).subscribe({
      next: (token) => {
        this.loginService.addToken(token);
        Swal.fire('Sucesso', 'Login realizado!', 'success');

        const role = this.loginService.getUsuarioCargo();
        if (role === 'COZINHA') {
          this.router.navigate(['/admin/pedidos/fila']);
        } else {
          this.router.navigate(['/admin/pedidos']);
        }
      },
      error: (err) => {
        console.error(err);
        Swal.fire('Erro', 'Usuário ou senha incorretos!', 'error');
      }
    });
  }
}
