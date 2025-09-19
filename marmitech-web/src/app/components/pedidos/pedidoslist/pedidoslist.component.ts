import { Component, inject, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import Swal from 'sweetalert2';
import { MdbModalModule, MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { Pedido } from '../../../models/pedido';
import { PedidoService } from '../../../services/pedido.service';
import { Produto } from '../../../models/produto';
import { ProdutoService } from '../../../services/produto.service';
import { Router } from '@angular/router';
import { Cliente } from '../../../models/cliente';
import { ClienteService } from '../../../services/cliente.service';

import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pedidoslist',
  imports: [MdbModalModule, CommonModule, FormsModule],
  templateUrl: './pedidoslist.component.html',
  styleUrl: './pedidoslist.component.scss',
})

export class PedidoslistComponent implements OnInit {

  constructor() { }
  lista: Pedido[] = [];

  categorias = [
    { nome: 'Marmitas' },
    { nome: 'Bebidas' },
    { nome: 'Sobremesas' },
    { nome: 'Outros' }
  ];

  produtos: Produto[] = [];

  vistaAtual: 'categorias' | 'produtos' = 'categorias';
  produtosDaCategoria: any[] = [];
  categoriaSelecionada: any = null;


  carrinho: { produto: any, quantidade: number }[] = [];
  valorTotalPedido: number = 0;

  modalService = inject(MdbModalService);
  @ViewChild('modalPedidoDetalhe') modalPedidoDetalhe!: TemplateRef<any>;
  @ViewChild('modalCategorias') modalCategorias!: TemplateRef<any>;
  modalRef!: MdbModalRef<any>;

  pedidoService = inject(PedidoService);
  produtoService = inject(ProdutoService);



  pedidoEdit: Pedido = new Pedido(
    {
      id: 0,
      nomeCliente: '',
      status: '',
      enderecoEntrega: '',
      pedidoItems: [],
      valorTotal: 0
    });



  ngOnInit(): void {
    this.findAllPedidos();
    this.findAllProdutos();
    this.carregarTodosClientes();
  }

  findAllPedidos() {
    this.pedidoService.findAll().subscribe({
      next: (listaRecebida: Pedido[]) => {
        this.lista = listaRecebida;
      },
      error: (err: any) => {
        Swal.fire({
          title: 'Erro ao buscar pedidos',
          text: 'Houve um problema ao se comunicar com o servidor.',
          icon: 'error'
        });
        console.error('Erro ao buscar pedido:', err);
      }
    });
  }
  findAllProdutos() {
    this.produtoService.findAll().subscribe({
      next: (produtosRecebidos: Produto[]) => {
        this.produtos = produtosRecebidos;
        console.log('Produtos carregados:', this.produtos);
      },
      error: (err: any) => {
        Swal.fire({
          title: 'Erro ao buscar produtos',
          icon: 'error'
        });
        console.error('Erro ao buscar produtos:', err);
      }
    });
  }

  editById(pedido: Pedido) {
    this.pedidoEdit = Object.assign({}, pedido);
    this.modalRef = this.modalService.open(this.modalPedidoDetalhe);
  }

  new() {
    this.pedidoEdit = new Pedido(
      {
        id: 0,
        nomeCliente: '',
        status: '',
        enderecoEntrega: '',
        pedidoItems: [],
        valorTotal: 0
      });
    this.modalRef = this.modalService.open(this.modalPedidoDetalhe);
  }


  retornoDetalhe(pedido: Pedido) {
    if (pedido.id) {
      this.lista = this.lista.map(p => p.id === pedido.id ? pedido : p);
    } else {
      pedido.id = this.lista.length + 1;
      this.lista.push(pedido);
    }
    this.modalRef.close();
  }


  deleteById(pedido: Pedido) {
    Swal.fire({
      title: 'Confirma o cancelamento do pedido ?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim',
      cancelButtonText: 'Não'
    }).then((result) => {
      if (result.isConfirmed) {
        this.pedidoService.delete(pedido.id).subscribe({
          next: () => {
            this.lista = this.lista.filter(p => p.id !== pedido.id);
            Swal.fire(
              'Excluído!',
              'Pedido excluído com sucesso.',
              'success'
            );
          },
          error: (err) => {
            Swal.fire('Erro!', 'Não foi possível excluir o pedido.', 'error');
            console.error(err);
          }
        });
      }
    });
  }

  selecionarCategoria(categoria: any) {
    this.categoriaSelecionada = categoria;
    this.produtosDaCategoria = this.produtos.filter(p =>
      p.categoria.toUpperCase() === categoria.nome.toUpperCase()
    );
    this.vistaAtual = 'produtos';
  }

  voltarParaCategorias() {
    this.vistaAtual = 'categorias';
    this.categoriaSelecionada = null;
    this.produtosDaCategoria = [];
  }

  adicionarAoCarrinho(produto: any) {
    // Procura se o item já existe no carrinho
    const itemExistente = this.carrinho.find(item => item.produto.id === produto.id);

    if (itemExistente) {
      // Se existe, apenas incrementa a quantidade
      itemExistente.quantidade++;
    } else {
      // Se não existe, adiciona o novo produto com quantidade 1
      this.carrinho.push({ produto: produto, quantidade: 1 });
    }

    this.calcularTotal();
    console.log('Carrinho atual:', this.carrinho);
  }

  calcularTotal() {
    this.valorTotalPedido = this.carrinho.reduce((total, item) => {
      return total + (item.produto.precoUnitario * item.quantidade);
    }, 0);
  }

  removerDoCarrinho(index: number) {
    this.carrinho.splice(index, 1);
    this.calcularTotal();
  }


  termoBuscaCliente: string = '';
  clientesEncontrados: Cliente[] = [];
  clienteSelecionado: Cliente | null = null;

  observacaoPedido: string = '';

  router = inject(Router);
  clienteService = inject(ClienteService);

  todosOsClientes: Cliente[] = []; // Para guardar a lista do dropdown
  clienteIdSelecionado: number | null = null; // Para guardar o ID do cliente escolhido

  carregarTodosClientes() {
    this.clienteService.findAll().subscribe({
      next: (clientes) => {
        this.todosOsClientes = clientes;
      },
      error: (err) => console.error('Erro ao carregar a lista de clientes:', err)
    });
  }

  buscarCliente() {
    if (this.termoBuscaCliente.trim() === '') {
      this.clientesEncontrados = [];
      return;
    }
    this.clienteService.findByNome(this.termoBuscaCliente).subscribe({
      next: (clientes) => {
        this.clientesEncontrados = clientes;
        if (clientes.length === 0) {
          alert('Nenhum cliente encontrado com este nome.');
        }
      },
      error: (err) => console.error('Erro ao buscar clientes:', err)
    });
  }

  selecionarCliente(cliente: Cliente) {
    this.clienteSelecionado = cliente;
    console.log('MOMENTO DA SELEÇÃO - clienteSelecionado é:', this.clienteSelecionado);
    this.clientesEncontrados = [];
    this.termoBuscaCliente = '';
  }

  finalizarPedido() {
    if (this.carrinho.length === 0) {
      Swal.fire('Erro', 'O carrinho está vazio.', 'error');
      return;
    }
    if (!this.clienteIdSelecionado) {
      Swal.fire('Erro', 'Por favor, selecione um cliente no menu.', 'error');
      return;
    }

    const itensDoPedido = this.carrinho.map(item => ({
      quantidade: item.quantidade,
      produto: { id: item.produto.id }
    }));

    const novoPedido: any = {
      status: 'FILA',
      enderecoEntrega: 'Balcão',
      valorTotal: this.valorTotalPedido,
      cliente: { id: this.clienteIdSelecionado },
      pedidoItems: itensDoPedido,
    };

    this.pedidoService.save(novoPedido).subscribe({
      next: (pedidoSalvo) => {
        Swal.fire('Sucesso!', `Pedido #${pedidoSalvo.id} finalizado!`, 'success');
        this.limparCarrinho();
      },
      error: (err) => {
        console.log(err);
        Swal.fire('Erro!', 'Não foi possível finalizar o pedido.', 'error');
      }
    });
  }

  limparCarrinho() {
    this.carrinho = [];
    this.valorTotalPedido = 0;
    this.observacaoPedido = '';
    this.clienteIdSelecionado = null;
  }

}
