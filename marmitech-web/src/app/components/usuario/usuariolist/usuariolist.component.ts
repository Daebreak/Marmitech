import { Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { Usuario } from '../../../models/usuario';
import { RouterLink } from '@angular/router';
import { UsuariosService } from '../../../services/usuario.service';
import { DatePipe } from '@angular/common';
import Swal from 'sweetalert2';
//import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { UsuariodetailsComponent } from '../usuariodetails/usuariodetails.component';
//import { UsuariodetailsComponent } from '../usuariodetails/usuariodetails.component';
import {
  MdbModalModule,
  MdbModalRef,
  MdbModalService,
} from 'mdb-angular-ui-kit/modal';
@Component({
  selector: 'app-usuariolist',
  imports: [RouterLink, MdbModalModule, UsuariodetailsComponent, DatePipe],
  templateUrl: './usuariolist.component.html',
  styleUrl: './usuariolist.component.scss',
  standalone: true,
})
export class UsuariolistComponent {
  lista: Usuario[] = [];
  usuarioService = inject(UsuariosService);
  usuarioEdit: Usuario = new Usuario({
    id: 0,
    nome: '',
    email: '',
    senha: '',
    cargo: '',
    dataCriacao: '',
  });
  /****Modal*********************************************** */
  modalService = inject(MdbModalService);
  @ViewChild('modalUsuariosDetalhe') modalUsuariosDetalhe!: TemplateRef<any>;
  modalRef!: MdbModalRef<any>;
  /*************************************************** */

  constructor() {
    this.findAll();
  }

  findAll() {
    this.usuarioService.findAll().subscribe({
      next: (lista: Usuario[]) => {
        console.log(lista);
        this.lista = lista;
      },
      error: (err) => {
        Swal.fire({
          title: 'Erro ao carregar lista de usuários',
          text: err.message,
          icon: 'error',
          confirmButtonText: 'Fechar',
        });
      },
    });
  }

  deleteById(usuario: Usuario) {
  Swal.fire({
        title: 'Confirma a exclusão do produto ' + usuario.nome + '?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sim',
        cancelButtonText: 'Não'
      }).then((result) => {
        if (result.isConfirmed) {
          this.usuarioService.delete(usuario.id!).subscribe({
            next: msg => {
              Swal.fire(
                'Excluído!',
                'Produto ' + usuario.nome + ' excluído com sucesso.',
                'success'
              );
              this.findAll();
            },
            error: err =>{
              Swal.fire(
                'Erro!',
                'Houve um erro ao executar esta ação: ' + err.error,
                'error'
              );
            }
          })
  
        }
      });
  }
  new() {
    this.usuarioEdit = new Usuario({
      id: 0,
      nome: '',
      email: '',
      senha: '',
      cargo: '',
      dataCriacao: '',
    });
    this.modalRef = this.modalService.open(this.modalUsuariosDetalhe);
  }
  editById(usuario: Usuario) {
    this.usuarioEdit = Object.assign({}, usuario); //clonando pra evitar referencia de objeto
    this.modalRef = this.modalService.open(this.modalUsuariosDetalhe);
  }
  retornoDetalhes(usuario: Usuario) {
    this.findAll();
    this.modalRef.close();
  }
}
