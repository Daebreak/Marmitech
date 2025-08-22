package com.marmitech.Marmitech.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.marmitech.Marmitech.DTO.PedidoItemResponseDTO;
import com.marmitech.Marmitech.DTO.PedidoResponseDTO;
import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Entity.PedidoItem;

public class PedidoResponseMapper {
    private static PedidoItemResponseDTO toItemDto(PedidoItem pedidoItem){
        return new PedidoItemResponseDTO(
            pedidoItem.getProduto().getNome(),
            pedidoItem.getQuantidade(),
            pedidoItem.getPrecoUnitarioPedido(),
            pedidoItem.getPrecoUnitarioPedido());
    }

    public static PedidoResponseDTO toDto(Pedido pedido){
        Set<PedidoItemResponseDTO> pedidoItemResponseDTOs = pedido.getPedidoItems()
        .stream()
        .map(PedidoResponseMapper::toItemDto)
        .collect(Collectors.toSet());

        return new PedidoResponseDTO(
            pedido.getCliente().getNome(), 
            pedido.getStatus(), 
            pedido.getEndereco_entrega(), 
            pedidoItemResponseDTOs, 
            pedido.getValor_total());
    }
}
