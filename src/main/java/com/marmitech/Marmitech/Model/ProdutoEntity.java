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
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer produto_id;
    private String nome;
    private String descricao;
    private String sku;
    private double preco_unitario;
    private int estoque;
    private String categoria;
    private String data_cadastro;
}
