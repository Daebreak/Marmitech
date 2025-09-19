import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-historicodetails',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './historicodetails.component.html',
  styleUrls: ['./historicodetails.component.scss']
})
export class HistoricodetailsComponent implements OnInit {

  //lista de dados estáticos para testes
  historicos = [
    {
      id: 1,
      cliente: 'João Silva',
      produtos: '1x marmita P',
      descricao: 'OK',
      status: 'FINALIZADO',
      dataPedido: '2025-09-02'
    },
    {
      id: 2,
      cliente: 'Maria Souza',
      produtos: '2x marmita M',
      descricao: 'OK',
      status: 'FINALIZADO',
      dataPedido: '2025-09-03'
    },
    {
      id: 3,
      cliente: 'Carlos Pereira',
      produtos: '2x marmita P',
      descricao: 'Sem feijao',
      status: 'PREPARANDO',
      dataPedido: '2025-09-04'
    },
    {
      id: 4,
      cliente: 'Ana Lima',
      produtos: '3x marmita g',
      descricao: '1 marmita sem salada',
      status: 'FILA',
      dataPedido: '2025-09-05'
    },
    {
      id: 5,
      cliente: 'Pedro Alves',
      produtos: 'Cliente cancelou o pedido',
      descricao: 'CANCELADO',
      status: 'CANCELADO',
      dataPedido: '2025-09-05'
    }
  ];

  constructor(private route: ActivatedRoute) { }

  historico: any; // Variável para guardar o pedido encontrado - teste
  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const id = +idParam;
      this.historico = this.historicos.find(h => h.id === id);
    }
  }
}