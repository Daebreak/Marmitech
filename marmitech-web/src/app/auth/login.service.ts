import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { jwtDecode } from "jwt-decode";
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    http = inject(HttpClient);
    API = `${environment.apiUrl}/api/login`;

    constructor() { }

    // Faz o POST no backend para pegar o token
    logar(loginData: any): Observable<string> {
        return this.http.post<string>(this.API, loginData, { responseType: 'text' as 'json' });
    }

    // Salva o token no navegador
    addToken(token: string) {
        localStorage.setItem('token', token);
    }

    // Remove o token
    removerToken() {
        localStorage.removeItem('token');
    }

    // Recupera o token salvo
    getToken() {
        return localStorage.getItem('token');
    }

    // Lê o cargo dentro do token
    getUsuarioCargo(): string {
        const token = this.getToken();
        if (token) {
            const decoded: any = jwtDecode(token);
            return (decoded.role || decoded.cargo || '').toUpperCase();
        }
        return '';
    }

    // Verifica se tem permissão
    hasRole(role: string): boolean {
        const cargoAtual = this.getUsuarioCargo();
        // Aceita tanto "ADMIN" quanto "ROLE_ADMIN"
        if (cargoAtual === role.toUpperCase() || cargoAtual === 'ROLE_' + role.toUpperCase()) {
            return true;
        }
        return false;
    }
}