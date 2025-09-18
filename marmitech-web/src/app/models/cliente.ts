export class Cliente {
  id!: number;
  nome!: string;
  email!: string;
  telefone!: string;
  dataCadastro!: string;

  // Construtor que recebe um objeto
  /*constructor(objeto: Partial<Cliente>) {
    Object.assign(this, objeto);
  }*/
 constructor(data:any){
  this.id = data.ClienteId;
  this.nome = data.nome;
  this.email = data.email;
  this.telefone = data.telefone;
  this.dataCadastro = data.dataCadastro;
 }
}
