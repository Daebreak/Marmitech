import { Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { Categoria } from '../../../models/categoria';
import { RouterLink } from '@angular/router';
import { CategoriasService } from '../../../services/categoria.service';
import Swal from 'sweetalert2';
import { CategoriasdetailsComponent } from '../categoriasdetails/categoriasdetails.component';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'app-categoriaslist',
  imports: [RouterLink, MdbModalModule, CategoriasdetailsComponent],
  templateUrl: './categoriaslist.component.html',
  styleUrl: './categoriaslist.component.scss',
  standalone: true,
})
export class CategoriaslistComponent {
  lista: Categoria[] = [];
  categoriaService = inject(CategoriasService);

  categoriaEdit: Categoria = new Categoria({
    id: 0,
    nome: '',
    descricao: '',
  });

  modalService = inject(MdbModalService);
  @ViewChild('modalCategoriaDetalhe') modalCategoriaDetalhe!: TemplateRef<any>;
  modalRef!: MdbModalRef<any>;

  constructor() {
    this.findAll();
  }

  findAll() {
    this.categoriaService.findAll().subscribe({
      next: (lista: Categoria[]) => {
        this.lista = lista;
      },
      error: (err: { message: any }) => {
        Swal.fire({
          title: 'Erro ao carregar lista de categorias',
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
        this.categoriaService.delete(categoria.id).subscribe({
          next: () => {
            this.lista = this.lista.filter(c => c.id !== categoria.id);
          },
          error: (err: { message: any }) => {
            Swal.fire({
              title: 'Erro ao deletar categoria',
              text: err.message,
              icon: 'error',
              confirmButtonText: 'Fechar',
            });
          },
        });
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
    this.categoriaEdit = Object.assign({}, categoria);
    this.modalRef = this.modalService.open(this.modalCategoriaDetalhe);
  }

  retornoDetalhes(categoria: Categoria) {
    if (categoria.id > 0) {
      this.categoriaService.update(categoria).subscribe({
        next: () => {
          Swal.fire({
            title: 'Sucesso!',
            text: 'Categoria atualizada com sucesso.',
            icon: 'success',
            confirmButtonText: 'OK',
          });
        },
        error: (err: { message: any }) => {
          Swal.fire({
            title: 'Erro ao atualizar categoria',
            text: err.message,
            icon: 'error',
            confirmButtonText: 'Fechar',
          });
        },
      });
    }

    this.modalRef.close();
  }
}
