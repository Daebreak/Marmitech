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
    const dto = {
      username: this.loginData.username,
      password: this.loginData.password
    };

    this.loginService.logar(dto).subscribe({
      next: (token) => {
        //Salva o token
        this.loginService.addToken(token);

        //Verifica o cargo para decidir o destino
        // O método getUsuarioCargo lê o token que acabamos de salvar
        const cargo = this.loginService.getUsuarioCargo().toUpperCase();

        if (cargo === 'ADMIN') {
          this.router.navigate(['/admin/usuarios']);
        }
        else if (cargo === 'COZINHA') {
          this.router.navigate(['/admin/pedidos/fila']);
        }
        else {
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