import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { from, switchMap, catchError, throwError } from 'rxjs';
import { KeycloakService } from './login.service';

export const httpInterceptor: HttpInterceptorFn = (request, next) => {
  const keycloak = inject(KeycloakService);

  // Atualiza o token antes de cada request (se necessário)
  return from(keycloak.updateToken()).pipe(
    switchMap(token => {
      const cloned = request.clone({
        setHeaders: { Authorization: 'Bearer ' + token }
      });
      return next(cloned);
    }),
    catchError((err: any) => {
      if (err instanceof HttpErrorResponse && (err.status === 401 || err.status === 403)) {
        keycloak.logout();
      }
      return throwError(() => err);
    })
  );
};