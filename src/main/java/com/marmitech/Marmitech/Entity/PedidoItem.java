package com.marmitech.Marmitech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int pedidoId;
    private int produtoId;
    private int quantidade;
    private int precoUnitarioPedido;
    private int subtotal;

    public PedidoItem(int pedidoId, int produtoId, int quantidade, int precoUnitarioPedido, int subtotal) {

        if (pedidoId <= 0) {
            throw new IllegalArgumentException("pedidoId não pode ser menor que 1.");
        }
        if (produtoId <= 0) {
            throw new IllegalArgumentException("produtoId não pode ser menor que 1");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("quantidade não pode ser menor que 1.");
        }
        if (precoUnitarioPedido < 0) {
            throw new IllegalArgumentException("precoUnitarioPedido não pode ser menor que 1.");
        }
        if (subtotal < 0) {
            throw new IllegalArgumentException("subtotal não pode ser menor que 1.");
        }

        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitarioPedido = precoUnitarioPedido;
        this.subtotal = subtotal;
    }

    
}
