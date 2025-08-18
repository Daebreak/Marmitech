package com.marmitech.Marmitech.Entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank(message = "quantidade não pode ser null ou vazio")
    private int quantidade;

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

    
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
