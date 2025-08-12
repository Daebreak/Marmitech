package com.marmitech.Marmitech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int clente_id;
    private String nome;
    private String cpf_cnpj;
    private String telefone;
    private String email;
    private String endereco;
    private String data_cadastro;


}
