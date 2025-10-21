import { Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { Categoria } from '../../../models/categoria';
import { RouterLink } from '@angular/router';
<<<<<<< HEAD
=======
import Swal from 'sweetalert2';
import { CategoriasdetailsComponent } from '../categoriasdetails/categoriasdetails.component';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
>>>>>>> marinaTest
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
<<<<<<< HEAD
  styleUrls: ['./categoriaslist.component.scss'],
=======
  styleUrl: './categoriaslist.component.scss',
>>>>>>> marinaTest
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

<<<<<<< HEAD
  constructor() {
    this.findAll();
  }

  // 🟢 Buscar todas as categorias
=======
  constructor(){
    this.findAll()
  }

>>>>>>> marinaTest
  findAll() {
    this.categoriaService.findAll().subscribe({
      next: (lista: Categoria[]) => {
        this.lista = lista;
      },
      error: (err: { message: any }) => {
        Swal.fire({
<<<<<<< HEAD
          title: 'Erro ao carregar lista de categorias',
=======
          title: 'Erro ao carregar lista de Categorias',
>>>>>>> marinaTest
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
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim, deletar!',
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
<<<<<<< HEAD
        this.categoriaService.delete(categoria.id).subscribe({
          next: () => {
            this.lista = this.lista.filter((c) => c.id !== categoria.id);
            Swal.fire({
              title: 'Deletado com sucesso!',
              icon: 'success',
              confirmButtonText: 'OK',
            });
=======
        this.cateService.delete(categoria.id).subscribe({
          next: () => {
            this.lista = this.lista.filter(c => c.id !== categoria.id);
>>>>>>> marinaTest
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
<<<<<<< HEAD
=======
        Swal.fire({
          title: 'Deletado com sucesso!',
          icon: 'success',
          confirmButtonText: 'OK',
        });
>>>>>>> marinaTest
      }
    });
  }

<<<<<<< HEAD
  // 🆕 Nova categoria
=======
>>>>>>> marinaTest
  new() {
    this.categoriaEdit = new Categoria({
      id: 0,
      nome: '',
      descricao: '',
    });
    this.modalRef = this.modalService.open(this.modalCategoriaDetalhe);
  }

<<<<<<< HEAD
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
=======
  editById(categoria: Categoria) {
    this.categoriaEdit = Object.assign({}, categoria);
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
>>>>>>> marinaTest
  }
}
}
