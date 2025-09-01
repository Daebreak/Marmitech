import { Component } from '@angular/core';

@Component({
  selector: 'app-pedidoslist',
  imports: [],
  templateUrl: './pedidoslist.component.html',
  styleUrl: './pedidoslist.component.scss'
})
export class PedidoslistComponent {
  lista = [
    {
      id: 1,
      nomeCliente: 'João Silva',
      status: 'Em andamento',
      enderecoEntrega: 'Rua A, 123',
      pedidoItems: [
        { nome: 'Produto 1', quantidade: 2, precoUnitario: 50 },
        { nome: 'Produto 2', quantidade: 1, precoUnitario: 100 }
      ],
      valorTotal: 200,
      data: '2024-06-01'
    },
    {
      id: 2,
      nomeCliente: 'Maria Oliveira',
      status: 'Concluído',
      enderecoEntrega: 'Avenida B, 456',
      pedidoItems: [
        { nome: 'Produto 3', quantidade: 1, precoUnitario: 150 },
        { nome: 'Produto 4', quantidade: 3, precoUnitario: 30 }
      ],
      valorTotal: 240,
      data: '2024-06-02'
    }
  ];

  editById(id: number) {
    console.log('Editando pedido com ID:', id);
  }

  deleteById(id: number) {
    console.log('Deletando pedido com ID:', id);
  }

  novoPedido() {
    console.log('Criando novo pedido');
  }

}
