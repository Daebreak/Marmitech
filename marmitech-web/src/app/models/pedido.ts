import { PedidoItem } from "./pedido-item";

export class Pedido {
    id!: number;
    nomeCliente!: string;
    status!: string;
    enderecoEntrega!: string;
    pedidoItems!: PedidoItem[];
    valorTotal!: number;
    data!: string;
}
