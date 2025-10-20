import { Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { Categoria } from '../../../models/categoria';
import { RouterLink } from '@angular/router';
import { CategoriaService } from '../../../services/categoria.service';
import Swal from 'sweetalert2';
import {
  MdbModalModule,
  MdbModalRef,
  MdbModalService,
} from 'mdb-angular-ui-kit/modal';
import { CategoriasdetailsComponent } from '../categoriasdetails/categoriasdetails.component';

@Component({
  selector: 'app-categoriaslist',
  imports: [RouterLink, MdbModalModule, CategoriasdetailsComponent],
  templateUrl: './categoriaslist.component.html',
  styleUrls: ['./categoriaslist.component.scss'],
  standalone: true,
})
export class CategoriaslistComponent {
  lista: Categoria[] = [];
  categoriaService = inject(CategoriaService);

  categoriaEdit: Categoria = new Categoria({
    id: 0,
    nome: '',
    descricao: '',
  });

  /****Modal*********************************************** */
  modalService = inject(MdbModalService);
  @ViewChild('modalCategoriaDetalhe') modalCategoriaDetalhe!: TemplateRef<any>;
  modalRef!: MdbModalRef<any>;
  /*************************************************** */

  constructor() {
    this.findAll();
  }

  // 🟢 Buscar todas as categorias
  findAll() {
    this.categoriaService.findAll().subscribe({
      next: (lista: Categoria[]) => {
        console.log(lista);
        this.lista = lista;
      },
      error: (err) => {
        Swal.fire({
          title: 'Erro ao carregar lista de categorias',
          text: err.message,
          icon: 'error',
          confirmButtonText: 'Fechar',
        });
      },
    });
  }

  // 🔴 Deletar categoria
  deleteById(categoria: Categoria) {
    if (!categoria.id) {
      Swal.fire({
        title: 'Categoria sem ID',
        icon: 'warning',
        confirmButtonText: 'Fechar',
      });
      return;
    }

    Swal.fire({
      title: 'Você tem certeza?',
      icon: 'warning',
      showConfirmButton: true,
      showCancelButton: true,
      confirmButtonText: 'Sim, deletar!',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.categoriaService.delete(categoria.id).subscribe({
          next: () => {
            this.lista = this.lista.filter((c) => c.id !== categoria.id);
            Swal.fire({
              title: 'Deletado com sucesso!',
              icon: 'success',
              confirmButtonText: 'OK',
            });
          },
          error: (err) => {
            Swal.fire({
              title: 'Erro ao deletar categoria',
              text: err.message,
              icon: 'error',
              confirmButtonText: 'Fechar',
            });
          },
        });
      }
    });
  }

  // 🆕 Nova categoria
  new() {
    this.categoriaEdit = new Categoria({
      id: 0,
      nome: '',
      descricao: '',
    });
    this.modalRef = this.modalService.open(this.modalCategoriaDetalhe);
  }

  // ✏️ Editar categoria
  editById(categoria: Categoria) {
    this.categoriaEdit = Object.assign({}, categoria); // Clona o objeto
    this.modalRef = this.modalService.open(this.modalCategoriaDetalhe);
  }

  // 🔁 Retorno de criação ou atualização
  retornoDetalhes(categoria: Categoria) {
    if (categoria.id > 0) {
      // Atualizar categoria existente
      this.categoriaService.update(categoria).subscribe({
        next: () => {
          Swal.fire({
            title: 'Sucesso!',
            text: 'Categoria atualizada com sucesso.',
            icon: 'success',
            confirmButtonText: 'OK',
          });
          this.findAll(); // Atualiza lista sem duplicar
          this.modalRef.close();
        },
        error: (err) => {
          Swal.fire({
            title: 'Erro ao atualizar categoria',
            text: err.message,
            icon: 'error',
            confirmButtonText: 'Fechar',
          });
        },
      });
    } else {
      // Criar nova categoria
      this.categoriaService.create(categoria).subscribe({
        next: () => {
          Swal.fire({
            title: 'Cadastrado com sucesso!',
            icon: 'success',
            confirmButtonText: 'OK',
          });
          this.findAll(); // Recarrega lista — não duplica
          this.modalRef.close();
        },
        error: (err) => {
          Swal.fire({
            title: 'Erro ao cadastrar categoria',
            text: err.message,
            icon: 'error',
            confirmButtonText: 'Fechar',
          });
        },
      });
    }
  }
}
