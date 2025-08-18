package com.marmitech.Marmitech.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank(message = "nome não pode ser null ou vazio")
    private String nome;

    @NotNull
    @NotBlank(message = "senha não pode ser null ou vazio")
    private String senha;

    @NotNull
    @NotBlank(message = "email não pode ser null ou vazio")
    private String email;

    @NotNull
    @NotBlank(message = "cargo não pode ser null ou vazio")
    private String cargo;

    private LocalDate data_criacao;


    //Um usuario(caixa) pode registrar varios pedidos
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Pedido> pedidos;
}
