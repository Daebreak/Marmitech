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
    API = `${environment.apiUrl}/api/usuario/login`;

    constructor() { }

    // Faz o POST no backend para logar
    logar(nome: string, senha: string): Observable<any> {
        return this.http.post<any>(`${this.API}?nome=${nome}&senha=${senha}`, {}, { responseType: 'text' as 'json' });
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
            try {
                const decoded: any = jwtDecode(token);
                // Tenta pegar de 'role' ou 'cargo' e limpa espaços
                const cargo = (decoded.role || decoded.cargo || '').toString().trim().toUpperCase();
                return cargo;
            } catch (e) {
                console.error('Erro ao decodificar token:', e);
            }
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
