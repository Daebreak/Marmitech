import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Pedido } from '../../../models/pedido';
import { PedidoService } from '../../../services/pedido.service';
import { CurrencyPipe, DatePipe, CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-historicolist',
  imports: [RouterLink, CurrencyPipe, DatePipe, CommonModule, FormsModule],
  templateUrl: './historicolist.component.html',
  styleUrl: './historicolist.component.scss'
})
export class HistoricolistComponent implements OnInit {

  constructor() { }

  pedidos: Pedido[] = [];
  pedidosOriginal: Pedido[] = [];

  filtroData: string = '';
  filtroStatus: string = '';

  private pedidoService = inject(PedidoService);

  ngOnInit(): void {
    this.carregarPedidos();
  }

  carregarPedidos() {
    this.pedidoService.findAll().subscribe({
      next: (listaRecebida) => {
        this.pedidos = listaRecebida;
        this.pedidosOriginal = listaRecebida; // Salva a lista original
      },
      error: (erro) => {
        console.error('Erro ao buscar a lista de pedidos:', erro);
        Swal.fire('Erro', 'Houve um erro ao carregar o histórico de pedidos.', 'error');
      }
    });
  }

  buscar() {
    let pedidosFiltrados = [...this.pedidosOriginal];

    if (this.filtroData) {
      pedidosFiltrados = pedidosFiltrados.filter(pedido => {
        // Converte a data do pedido para o formato YYYY-MM-DD para comparação
        const dataPedidoFormatada = new Date(pedido.dataPedido).toISOString().split('T')[0];
        return dataPedidoFormatada === this.filtroData;
      });
    }

    // Aplica filtro de status se houver
    if (this.filtroStatus) {
      pedidosFiltrados = pedidosFiltrados.filter(pedido =>
        pedido.status.toUpperCase() === this.filtroStatus.toUpperCase()
      );
    }

    this.pedidos = pedidosFiltrados;
  }

  // Função para limpar os filtros e restaurar a lista
  limparFiltros() {
    this.filtroData = '';
    this.filtroStatus = '';
    this.pedidos = [...this.pedidosOriginal];
  }
}