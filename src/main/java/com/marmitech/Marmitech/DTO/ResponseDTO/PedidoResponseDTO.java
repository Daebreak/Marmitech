package com.marmitech.Marmitech.DTO.ResponseDTO;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Builder;

@Builder
public record PedidoResponseDTO(
        //Colocando o id para aparecer no hostorico do front
        int id,
        String nomeCliente,
        String status,
        String enderecoEntrega,
        Set<PedidoItemResponseDTO> pedidoItems,
        BigDecimal valorTotal,
        String dataPedido
) {
}