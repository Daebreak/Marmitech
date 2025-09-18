import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Cliente } from '../../../models/cliente';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'
import {clienteService } from '../../../service/cliente.service';
@Component({
  selector: 'app-usuariodetails',
  imports: [MdbFormsModule, FormsModule,clienteDetailsComponent],
  templateUrl: './clientedetails.component.html',
  styleUrl: './clientedetails.component.scss',
  standalone: true,
})
export class clienteDetailsComponent {
  

  @Input("clienteEdit") clienteEdit: Cliente = new Cliente({});
  @Output("retorno") retorno = new EventEmitter<any>();
 
  route = inject(ActivatedRoute);
  routerSaver = inject(Router);
 usuarioService = inject(Cliente);
  clienteService: any;
  cliente: any;
  
 constructor() {
    const id = this.route.snapshot.paramMap.get('id');
    console.log(id);

    if (id) {
      
      this.clienteService.findById(parseInt(id)); 
    }
}
  
  
  salvar() {
    if (this.cliente.id > 0) {
      this.clienteService.update(this.cliente).subscribe({
        next: (updatedCliente: Cliente) => {
          Swal.fire({
            title: 'Atualizado!',
            icon: 'success',
            confirmButtonText: 'OK',
          });
          this.cliente = updatedCliente;
        },
        error: (err: { message: any; }) => {
          Swal.fire({
            title: 'Erro ao atualizar pessoa',
            text: err.message,
            icon: 'error',
            confirmButtonText: 'Fechar',
          });
        },
      });
    } else {
      this.clienteService.create(this.cliente).subscribe({
        next: (createdCliente: Cliente) => {
          Swal.fire({
            title: 'Criado!',
            icon: 'success',
            confirmButtonText: 'OK',
          });
          this.cliente = createdCliente;
        },
        error: (err: { message: any; }) => {
          Swal.fire({
            title: 'Erro ao criar usuário',
            text: err.message,
            icon: 'error',
            confirmButtonText: 'Fechar',
          });
        },
      });
     this.routerSaver.navigate(['admin/cliente'], {state: { clienteNovo: this.cliente }});
    }
    this.retorno.emit(this.cliente);
  }
}