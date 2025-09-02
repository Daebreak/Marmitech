package com.marmitech.Marmitech.Entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@NotNull
    private int quantidade;

    //@NotNull
    private BigDecimal precoUnitarioPedido;

    //@NotNull
    private BigDecimal subtotal;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
