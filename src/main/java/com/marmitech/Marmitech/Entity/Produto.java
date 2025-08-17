package com.marmitech.Marmitech.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long produto_id;
    private String sku;
    private String nome;
    private String descricao;
    private int preco_unitario;
    private String categoria;

    private LocalDateTime data_cadastro;


}
