export class Cliente {
    id!: number;
    nome!: string;
    telefone!: string;
    endereco!: string;
    email!: string;
    cpf!: string;
    dataCadastro!: string;

     constructor(data:any){
  this.id = data.ClienteId;
  this.nome = data.nome;
  this.email = data.email;
  this.telefone = data.telefone;
  this.endereco = data.endereco;
  this.cpf = data.cpf;
  this.dataCadastro = data.dataCadastro;
 }
}