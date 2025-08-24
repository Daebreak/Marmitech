import { Component, inject } from '@angular/core';
import { Produto } from '../../../models/produto';
import { RouterLink } from "../../../../../node_modules/@angular/router/router_module.d-Bx9ArA6K";

@Component({
  selector: 'app-produtoslist',
  imports: [RouterLink],
  templateUrl: './produtoslist.component.html',
  styleUrl: './produtoslist.component.scss'
})
export class ProdutoslistComponent {
  lista: Produto[] = [];

  constructor() {
    this.lista.push( new Produto({
      nome: 'Produto 1',
      descricao: 'Descrição do Produto 1',
      categoria: 'Categoria A',
      dataCadastro: '2023-10-01',
      precoUnitario: 100.00,
      estoque: 50,
      sku: 'SKU001'
    }) );

    this.lista.push( new Produto({
      nome: 'Produto 2',
      descricao: 'Descrição do Produto 2',
      categoria: 'Categoria B',
      dataCadastro: '2023-10-05',
      precoUnitario: 150.00,
      estoque: 30,
      sku: 'SKU002'
    }) );

    this.lista.push( new Produto({
      nome: 'Produto 3',
      descricao: 'Descrição do Produto 3',
      categoria: 'Categoria A',
      dataCadastro: '2023-10-10',
      precoUnitario: 200.00,
      estoque: 20,
      sku: 'SKU003'
    }) );
  }

  deletar(){
    //
  }
}
