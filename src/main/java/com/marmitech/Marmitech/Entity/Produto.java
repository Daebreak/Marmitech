package com.marmitech.Marmitech.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sku;
    private String nome;
    private String descricao;
    private int preco_unitario;
    private String categoria;
    private String data_cadastro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoItem> pedidoItems = new HashSet<>();

    // public Produto(String sku, String nome, String descricao, int preco_unitario, String categoria, int usuario_id, LocalDateTime data_cadastro) {
    //     if (produto_id <= 0) {
    //         throw new IllegalArgumentException( "produto_id nao pode ser menor que zero" );
    //     }
    //     if (sku == null || sku.isBlank()) {
    //         throw new IllegalArgumentException( "sku nao pode ser null ou vazio" );
    //     }
    //     if (nome == null || nome.isBlank()) {
    //         throw new IllegalArgumentException( "nome nao pode ser null ou vazio" );
    //     }
    //     if (descricao == null || descricao.isBlank()) {
    //         throw new IllegalArgumentException( "descricao nao pode ser null ou vazio" );
    //     }
    //     if (preco_unitario <= 0) {
    //         throw new IllegalArgumentException( "preco_unitario nao pode ser menor que zero" );
    //     }
    //     if (categoria == null || categoria.isBlank()) {
    //         throw new IllegalArgumentException( "categoria nao pode ser null ou vazio" );
    //     }
    //     if (usuario_id <= 0) {
    //         throw new IllegalArgumentException( "usuario_id nao pode ser menor que zero" );
    //     }
    //     if (data_cadastro == null || data_cadastro.isBefore( LocalDateTime.now() )) {
    //         throw new IllegalArgumentException( "data_cadastro não pode ser null e deve ser no presente" );
    //     }
    // }
}
