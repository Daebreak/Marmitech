import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {
  private keycloak: Keycloak;

  constructor() {
    this.keycloak = new Keycloak({
      url: 'http://localhost:5001',
      realm: 'marmitech',
      clientId: 'marmitech-web'
    });
  }


  async init(): Promise<boolean> {
    const authenticated = await this.keycloak.init({
      onLoad: 'login-required',
      checkLoginIframe: false
    });
    return authenticated;
  }

  getToken(): string | undefined {
    return this.keycloak.token;
  }

  async updateToken(): Promise<string> {
    await this.keycloak.updateToken(30);
    return this.keycloak.token!;
  }

  login(): void {
    this.keycloak.login({ redirectUri: 'http://localhost:5001' });
  }

  logout(): void {
    this.keycloak.logout({ redirectUri: 'http://localhost:5001' });
  }

  getUserRoles(): string[] {
    const realmAccess = this.keycloak.tokenParsed?.['realm_access'];
    return realmAccess?.['roles'] || [];
  }

  hasRole(role: string): boolean {
    return this.keycloak.hasRealmRole(role);
  }

  getUsername(): string | undefined {
    return this.keycloak.tokenParsed?.['preferred_username'];
  }

  isAuthenticated(): boolean {
    return !!this.keycloak.authenticated;
  }

  getUserCargo(): string {
    const roles = this.getUserRoles().filter(
      r => !['offline_access', 'uma_authorization', 'default-roles-marmitech'].includes(r)
    );
    return roles.length > 0 ? roles[0].toUpperCase() : '';
  }

  getUsuarioCargo(): string {
    return this.getUserCargo();
  }
}