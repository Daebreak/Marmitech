import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Produto } from '../models/produto';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  http = inject(HttpClient);

  API = "192.168.100.146:8080/api/produto";

  constructor() { }

  findAll(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.API+"/findAll");
  }
}
