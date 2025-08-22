package com.marmitech.Marmitech.Mapper.ResponseMapper;

import java.math.BigDecimal;

import com.marmitech.Marmitech.DTO.ResponseDTO.PedidoItemResponseDTO;
import com.marmitech.Marmitech.Entity.PedidoItem;

public class ProdutoItemResponseMapper {
    public static PedidoItemResponseDTO toDto(PedidoItem pedidoItem){
        return new PedidoItemResponseDTO(
            pedidoItem.getProduto().getNome(),
            pedidoItem.getQuantidade(),
            pedidoItem.getPrecoUnitarioPedido(),
            pedidoItem.getSubtotal()
        );
    }
}
