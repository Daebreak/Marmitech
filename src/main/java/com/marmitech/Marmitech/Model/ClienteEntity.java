package com.marmitech.Marmitech.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long cliente_id;
    private String nome;
    private String cpf_cnpj;
    private String telefone;
    private String email;
    private String endereco;
    private String data_cadastro;


}
