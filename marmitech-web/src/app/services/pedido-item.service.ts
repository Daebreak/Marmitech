import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PedidoItem } from '../models/pedido-item';

@Injectable({
  providedIn: 'root'
})
export class PedidoItemService {

  http = inject(HttpClient);

  API = "http://192.168.122.219:8080/pedidoItem";

  constructor() { }

  findall(): Observable<PedidoItem[]> {
    return this.http.get<PedidoItem[]>(`${this.API}/findAll`);
  }

  findById(id: number): Observable<PedidoItem>{
    return this.http.get<PedidoItem>(`${this.API}/${id}`);
  }

  save (pedidoItem: PedidoItem): Observable<PedidoItem>{
    return this.http.post<PedidoItem>(`${this.API}/save`, pedidoItem);
  }

  delete (id: number): Observable<string>{
    return this.http.delete<string>(`${this.API}/delete/${id}`, {responseType: 'text' as 'json'});
  }

  update(pedidoItem: PedidoItem): Observable<PedidoItem>{
    return this.http.put<PedidoItem>(`${this.API}/update/${pedidoItem.id}`, pedidoItem);
  }
}
