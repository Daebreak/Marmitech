import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { HistoricoCompras } from '../../../models/historico-compras';
import { HistoricoComprasService } from '../../../services/historico-compras.service';
import { Pedido } from '../../../models/pedido';
import { PedidoService } from '../../../services/pedido.service';
import { CurrencyPipe, DatePipe } from '@angular/common';


@Component({
  selector: 'app-historicolist',
  imports: [RouterLink, CurrencyPipe, DatePipe],
  templateUrl: './historicolist.component.html',
  styleUrl: './historicolist.component.scss'
})
export class HistoricolistComponent implements OnInit {

  constructor() { }

  historicos: HistoricoCompras[] = [];
  pedidos: Pedido[] = [];

  private historicoService = inject(HistoricoComprasService);
  private pedidoService = inject(PedidoService);

  ngOnInit(): void {
    this.carregarHistorico();
    this.carregarPedidos();
  }

  carregarHistorico() {
    this.historicoService.findAll().subscribe({
      next: (listaRecebida) => {
        this.historicos = listaRecebida;
      },
      error: (erro) => {
        console.error('Erro ao buscar o histórico:', erro);
        alert('Houve um erro ao carregar o histórico.');
      }
    });
  }

  carregarPedidos() {
    this.pedidoService.findAll().subscribe({
      next: (listaRecebida) => {
        this.pedidos = listaRecebida;
      },
      error: (erro) => {
        console.error('Erro ao buscar a lista de pedidos:', erro);
      }
    });
  }
}