package com.marmitech.Marmitech.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cliente_id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf_cnpj;
    private String endereco;
    private LocalDateTime data_cadastro;

    public Cliente(int cliente_id, String nome, String email, String telefone, String cpf_cnpj, LocalDateTime data_cadastro) {
        if (cliente_id <= 0) {
            throw new IllegalArgumentException( "cliente_id nao pode ser menor que zero" );
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException( "nome nao pode ser null ou vazio" );
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException( "email nao pode ser null ou vazio" );
        }
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException( "telefone nao pode ser null ou vazio" );
        }
        if (cpf_cnpj == null || cpf_cnpj.isBlank()) {
            throw new IllegalArgumentException( "cpf_cnpj nao pode ser null ou vazio" );
        }
        if (data_cadastro == null || data_cadastro.isBefore( LocalDateTime.now() )) {
            throw new IllegalArgumentException( "data_cadastro nao pode ser null ou no futuro" );
        }
        this.cliente_id = cliente_id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf_cnpj = cpf_cnpj;
        this.data_cadastro = data_cadastro;

    }
@OneToMany(mappedBy = "cliente")
   private List<Pedido> pedidos;
}
