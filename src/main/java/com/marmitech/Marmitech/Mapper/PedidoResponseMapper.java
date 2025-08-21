package com.marmitech.Marmitech.Mapper;

import com.marmitech.Marmitech.DTO.PedidoResponseDTO;
import com.marmitech.Marmitech.Entity.Pedido;

public class PedidoResponseMapper {
    public static PedidoResponseDTO toDto(Pedido pedido){
        return new PedidoResponseDTO(
            pedido.getCliente().getNome(), 
            pedido.getStatus(), 
            pedido.getEndereco_entrega(), 
            pedido.getPedidoItems(), 
            pedido.getValor_total());
    }
}
