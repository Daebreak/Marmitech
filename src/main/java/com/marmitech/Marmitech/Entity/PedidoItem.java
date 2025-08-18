package com.marmitech.Marmitech.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private int produtoId;
    private int pedidoId;
    private int produtoId;

    @NotNull
    @NotBlank(message = "quantidade não pode ser null ou vazio")
    private int quantidade;


    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull
    @NotBlank(message = "precoUnitarioPedido não pode ser null ou vazio")
    private BigDecimal precoUnitarioPedido;

    @NotNull
    @NotBlank(message = "subTotal não pode ser null ou vazio")
    private BigDecimal subtotal;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
