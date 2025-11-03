import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { LoginService } from '../../../auth/login.service';
import { Login } from '../../../auth/login';

@Component({
  selector: 'app-login',
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
 // usuario!: string;
  //senha!: string;
login: Login = new Login();
  router = inject(Router);
 loginService = inject(LoginService);
 
 logar() {
    this.loginService.logar(this.login).subscribe({
   next:token =>{

   },
   error:err =>{
    
  }
});
  }


}
