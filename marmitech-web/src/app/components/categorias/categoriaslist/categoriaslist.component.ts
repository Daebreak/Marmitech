import { Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { Categoria } from '../../../models/categoria';
import { RouterLink } from '@angular/router';
import Swal from 'sweetalert2';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { CategoriasdetailsComponent } from '../categoriasdetails/categoriasdetails.component';
import { CategoriaService } from '../../../services/categoria.service';

@Component({
  selector: 'app-categoriaslist',
  imports: [RouterLink, MdbModalModule, CategoriasdetailsComponent],
  templateUrl: './categoriaslist.component.html',
  styleUrls: ['./categoriaslist.component.scss'],
})
export class CategoriaslistComponent {
  lista: Categoria[] = [];
  cateService = inject(CategoriaService);

  categoriaEdit: Categoria = new Categoria(
    { id: 0, nome: '', descricao: '' }
  );

  modalService = inject(MdbModalService);
  @ViewChild('modalCategoriaDetalhe') modalCategoriaDetalhe!: TemplateRef<any>;
  modalRef!: MdbModalRef<any>;

  constructor(){
    this.findAll()
  }

  findAll() {
    this.cateService.findAll().subscribe({
      next: (lista: Categoria[]) => {
        this.lista = lista;
      },
      error: (err) => {
        Swal.fire({
          title: 'Erro ao carregar lista de Categorias',
          text: err.message,
          icon: 'error',
          confirmButtonText: 'Fechar',
        });
      },
    });
  }

  deleteById(categoria: Categoria) {
    Swal.fire({
      title: 'Você tem certeza?',
      icon: 'warning',
      showConfirmButton: true,
      showDenyButton: true,
      confirmButtonText: 'Sim, deletar!',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        //let indice = this.lista.findIndex((c) => {
        this.cateService.delete(categoria.id).subscribe({
          next: () => {
            this.lista = this.lista.filter((c) => c.id !== categoria.id);
          },
          error: (err) => {
            Swal.fire({
              title: 'Erro ao deletar usuário',
              text: err.message,
              icon: 'error',
              confirmButtonText: 'Fechar',
            });
          },
        });
        //this.lista.splice(indice, 1);
        Swal.fire({
          title: 'Deletado com sucesso!',
          icon: 'success',
          confirmButtonText: 'OK',
        });
      }
    });
  }
  new() {
    this.categoriaEdit = new Categoria({
      id: 0,
      nome: '',
      descricao: '',
    });
    this.modalRef = this.modalService.open(this.modalCategoriaDetalhe);
  }
  editById(categoria: Categoria) {
    this.categoriaEdit = Object.assign({}, categoria); //clonando pra evitar referencia de objeto
    this.modalRef = this.modalService.open(this.modalCategoriaDetalhe);
  }

// ... inject(CategoriaService) ...

retornoDetalhes(categoria: Categoria) {
  if (!categoria.id) {
    this.cateService.create(categoria).subscribe({
      next: (novaCategoria) => {
        // Sucesso! Adiciona na lista, fecha o modal, mostra o Swal.fire
        this.lista.push(novaCategoria);
        this.modalRef.close();
        Swal.fire({ title: 'Cadastrado!', icon: 'success' });
      },
      error: (err) => {
        // Deu erro, mostra mensagem de erro
        Swal.fire({ title: 'Erro ao cadastrar', text: err.message, icon: 'error' });
      }
    });
  } 
  // Se tem ID, é uma atualização
  else {
    this.cateService.update(categoria).subscribe({
      next: (categoriaAtualizada) => {
        // Sucesso na atualização!
        this.modalRef.close();
        Swal.fire({ title: 'Atualizado!', icon: 'success' });
      },
      error: (err) => {
        // Deu erro, mostra mensagem de erro
        Swal.fire({ title: 'Erro ao atualizar', text: err.message, icon: 'error' });
      }
    });
  }
}
}
