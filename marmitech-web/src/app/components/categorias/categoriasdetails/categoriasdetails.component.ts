import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { Categoria } from '../../../models/categoria';
import { CategoriaService } from '../../../services/categoria.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-categoriasdetails',
  standalone: true,
  imports: [MdbFormsModule, FormsModule],
  templateUrl: './categoriasdetails.component.html',
  styleUrls: ['./categoriasdetails.component.scss']
})
export class CategoriasdetailsComponent {

  @Input('categoria') categoria: Categoria = new Categoria({
    id: 0,
    nome: '',
    descricao: ''
  });

  @Output('retorno') retorno = new EventEmitter<any>();

  route = inject(ActivatedRoute);
  routerSaver = inject(Router);
  categoriaService = inject(CategoriaService);

  constructor() {
    /*const id = this.route.snapshot.paramMap.get('id');
    console.log(id);*/  }
  categoriaServices = inject(CategoriaService);

  salvar() {
    if (this.categoria.id > 0) {
      // 🟠 Atualizar categoria existente
      this.categoriaService.update(this.categoria).subscribe({
        next: (updatedCategoria) => {
          Swal.fire({
            title: 'Atualizado!',
            icon: 'success',
            confirmButtonText: 'OK',
          });
          this.categoria = updatedCategoria;
          this.retorno.emit(this.categoria);
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
      // 🔵 Criar nova categoria
      this.categoriaService.create(this.categoria).subscribe({
        next: (createdCategoria) => {
          Swal.fire({
            title: 'Criado!',
            icon: 'success',
            confirmButtonText: 'OK',
          });
          this.categoria = createdCategoria;
          // ✅ Aqui já vem com o ID real do banco
          this.routerSaver.navigate(['admin/categorias'], {
            state: { categoriaNovo: this.categoria },
          });
          this.retorno.emit(this.categoria);
        },
        error: (err) => {
          Swal.fire({
            title: 'Erro ao criar categoria',
            text: err.message,
            icon: 'error',
            confirmButtonText: 'Fechar',
          });
        },
      });
      this.routerSaver.navigate(['admin/categorias'], { state: { categoriaNovo: this.categoria } });
    }
    this.retorno.emit(this.categoria);
  }
}
