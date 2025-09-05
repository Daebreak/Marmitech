package com.marmitech.Marmitech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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

    @NotNull
    @NotBlank(message = "sku não pode ser null ou vazio")
    private String sku;

    @NotNull
    @NotBlank(message = "nome não pode ser null ou vazio")
    private String nome;

    @NotNull
    @NotBlank(message = "descricao não pode ser null ou vazio")
    private String descricao;

    @NotNull(message = "preco_unitario não pode ser null ou vazio")
    @Min(1)
    private BigDecimal precoUnitario;

    @NotNull(message = "estoque não pode ser null ou vazio")
    @Min(1)
    private int estoque;

    @NotNull
    @NotBlank(message = "categoria não pode ser null ou vazio")
    private String categoria;
    //private int usuario_id; //tabela do usuario
    private String dataCadastro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
    private Set<PedidoItem> pedidoItems = new HashSet<>();

}
