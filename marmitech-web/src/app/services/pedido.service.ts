// pedido.service.ts
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pedido } from '../models/pedido';


@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  API = 'http://localhost:8080/api/pedido';
  http = inject(HttpClient);

  constructor() { }

  findAll(): Observable<Pedido[]> {
    return this.http.get<Pedido[]>(this.API);
  }

  findById(id: number): Observable<Pedido> {
    return this.http.get<Pedido>(`${this.API}/findById/${id}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/delete/${id}`);
  }

  save(pedido: Pedido): Observable<Pedido> {
    return this.http.post<Pedido>(`${this.API}/save`, pedido);
  }

  update(pedido: Pedido): Observable<Pedido> {
    return this.http.put<Pedido>(`${this.API}/update/${pedido.id}`, pedido);
  }

}