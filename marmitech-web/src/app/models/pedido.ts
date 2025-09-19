import { PedidoItem } from "./pedido-item";

export class Pedido {
    id!: number;
    nomeCliente!: string;
    status!: string;
    enderecoEntrega!: string;
    pedidoItems!: PedidoItem[];
    valorTotal!: number;

    constructor(data: any) {
        this.id = data.id;
        this.nomeCliente = data.nomeCliente;
        this.status = data.status;
        this.enderecoEntrega = data.enderecoEntrega;
        this.pedidoItems = data.pedidoItems;
        this.valorTotal = data.valorTotal;
    }
}
