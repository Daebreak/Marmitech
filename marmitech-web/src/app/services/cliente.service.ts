import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente';


@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private API = 'http://localhost:8080/api/cliente';
  private http = inject(HttpClient);

  findByNome(nome: string): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.API}/findByNome/${nome}`);
  }

  findAll(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.API}`);
  }

}