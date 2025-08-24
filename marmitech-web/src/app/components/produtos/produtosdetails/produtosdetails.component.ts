import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Produto } from '../../../models/produto';


@Component({
  selector: 'app-produtosdetails',
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './produtosdetails.component.html',
  styleUrl: './produtosdetails.component.scss'
})
export class ProdutosdetailsComponent {
  produto: Produto = new Produto(
    { id: 0, 
      nome: '', 
      descricao: '', 
      categoria: '',
      dataCadastro: '', 
      precoUnitario: 0, 
      estoque: 0, 
      sku: '' });

  salvar() {
    alert('Produto salvo com sucesso!');
  }
}
