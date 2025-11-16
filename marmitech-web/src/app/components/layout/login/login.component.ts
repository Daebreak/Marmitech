import { Component, inject } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { Router } from "@angular/router";
import { MdbFormsModule } from "mdb-angular-ui-kit/forms";
import { LoginService } from "../../../auth/login.service";
import { Login } from "../../../auth/login";

@Component({
  selector: "app-login",
  imports: [MdbFormsModule, FormsModule],
  templateUrl: "./login.component.html",
  styleUrl: "./login.component.scss",
})
export class LoginComponent {
  // usuario!: string;
  //senha!: string;
  login: Login = new Login();
  router = inject(Router);
  loginService = inject(LoginService);

  constructor() {
    this.loginService.removerToken();
  }

  logar() {
    this.loginService.logar(this.login).subscribe({
      next: (token) => {
        // console.log(token);

        if (token) {
          this.loginService.addToken(token);

          this.router.navigate(["/admin/produtos"]);
        } else {
          alert("usuario ou senha incorretos");
        }

        if (this.loginService.hasPermission("Cozinha")) {
          this.router.navigate(["/admin/pedidos"]);
        } else if (this.loginService.hasPermission("Caixa")) {
          this.router.navigate(["/admin/produtos"]);
        } else if (this.loginService.hasPermission("admin")) {
          this.router.navigate(["/admin/produtos"]);
        }
      },

      error: (erro) => {
        alert("deu erro");
      },
    });
  }
}
