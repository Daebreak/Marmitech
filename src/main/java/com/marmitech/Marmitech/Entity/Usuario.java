package com.marmitech.Marmitech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioId;

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

    private LocalDateTime data_criacao;
}
