package com.marmitech.Marmitech.DTO;

import java.math.BigDecimal;
import java.util.Set;

import com.marmitech.Marmitech.Entity.PedidoItem;

import lombok.Builder;


@Builder
public record PedidoResponseDTO(String nomeCliente, String status, String endereco_entrega, Set<PedidoItem> pedidoItems, BigDecimal valor_total) {

}
