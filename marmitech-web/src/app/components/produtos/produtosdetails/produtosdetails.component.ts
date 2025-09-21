import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Produto } from '../../../models/produto';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ProdutoService } from '../../../services/produto.service';

@Component({
  selector: 'app-produtosdetails',
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './produtosdetails.component.html',
  styleUrl: './produtosdetails.component.scss'
})
export class ProdutosdetailsComponent {
  router = inject(ActivatedRoute);
  routerSaver = inject(Router);
  
  constructor() {
    const id = this.router.snapshot.paramMap.get('id');
    console.log(id);
    if (id) {
      this.findById(parseInt(id));
    }
  }
  
  produtoService = inject(ProdutoService);

  @Input("produto") produto: Produto = new Produto(
    { id: 0, 
      nome: '', 
      descricao: '', 
      categoria: '',
      dataCadastro: '', 
      precoUnitario: 0, 
      estoque: 0, 
      sku: '' });

  @Output("retorno") retorno = new EventEmitter<Produto>();
      
    findById(id: number) {
      this.produtoService.findById(id).subscribe(
        {
          next: produto => {
            this.produto = produto;
          },
          error: err => {
            Swal.fire(
              'Erro!',
              'Houve um erro ao executar esta ação: ' + err.error,
              'error'
            );
          }
        }
      )
    }

    salvar() {
    if(this.produto.id) {
      console.log('Enviando para atualização:', this.produto);
      this.produtoService.update(this.produto).subscribe(
        {
          next: produto => {
            Swal.fire(
              'Atualizado!',
              'Produto: ' + this.produto.nome + ' atualizado com sucesso.',
              'success'
            );      
            this.routerSaver.navigate(['admin/produtos'], {state: { produtoEditado: this.produto }});   
            this.retorno.emit(this.produto);
          },
          error: err => {
            Swal.fire(
              'Erro!',
              'Houve um erro ao executar esta ação: ' + err.error,
              'error'
            );
          }
        }
      )
      
    } else {

      this.produtoService.save(this.produto).subscribe(
        {
          next: produto => {
            Swal.fire(
              'Salvo!',
              'Produto: ' + this.produto.nome + ' atualizado com sucesso.',
              'success'
            );           
          
            this.routerSaver.navigate(['admin/produtos'], {state: { produtoNovo: this.produto }}); 
            this.retorno.emit(this.produto);
          },
          error: err => {
            Swal.fire(
              'Erro!',
              'Houve um erro ao executar esta ação: ' + err.error,
              'error'
            );
          }
        }
      )
    }

  }
}
