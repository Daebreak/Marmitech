package com.marmitech.Marmitech.DTO.ResponseDTO;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Builder;


@Builder
public record PedidoResponseDTO(String nomeCliente, String status, String endereco_entrega, Set<PedidoItemResponseDTO> pedidoItems, BigDecimal valor_total) {

}
