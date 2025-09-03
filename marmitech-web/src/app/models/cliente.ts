export class Cliente {
    clienteId:number;
    nome:string;
    email: string;
    telefone:string;
    dataCadastro:string;

    constructor(clienteId:number,nome:string,email:string,telefone:string, dataCadastro:string){
        this.clienteId = clienteId
        this.nome = nome
        this.email = email
        this.telefone = telefone
        this.dataCadastro = dataCadastro
    }
}
