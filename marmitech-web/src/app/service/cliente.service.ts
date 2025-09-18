import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente';

@Injectable({
  providedIn: 'root'
})
export class clienteService {
 

  private http = inject(HttpClient);

  private API = 'http://localhost:8080/api/cliente';

  constructor() { }

  findAll(): Observable<Cliente[]> {
    
    return this.http.get<Cliente[]>(`${this.API}/findAll` );

  }

  findById(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.API}findById/${id}`);
  }

  create(Cliente: Cliente): Observable<Cliente> {
  return this.http.post<Cliente>( `${this.API}/save`, Cliente );
}


  update(Cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.API}/update/${Cliente.id}`, Cliente);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/delete/${id}`);
  }
}
