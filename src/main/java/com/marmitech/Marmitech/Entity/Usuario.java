package com.marmitech.Marmitech.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioId;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private LocalDateTime data_criacao;



    public Usuario(int usuarioId, String nome, String email, String cargo, LocalDateTime data_criacao) {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException( "usuarioId não pode ser menor que 1" );
        }
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException( "nome não pode ser null ou vazio" );
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException( "email não pode ser null ou vazio" );
        }
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException( "cargo não pode ser null ou vazio" );
        }
        if (data_criacao == null || data_criacao.isBefore( LocalDateTime.now() )) {
            throw new IllegalArgumentException( "data_criacao não pode ser null e deve ser no presente" );
        }

        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.data_criacao = data_criacao;
    }

    @OneToMany(mappedBy = "usuario")

    private List<Pedido> pedidos ;
}
