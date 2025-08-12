package com.marmitech.Marmitech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class cliente {
    @Id
    int cliente_id;
    String nome;
    String email;
    String telefone;
    String cpf_cnpj;
    String Endereco;
    String data_Cadastro;
}
