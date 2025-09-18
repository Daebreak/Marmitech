package com.marmitech.Marmitech.DTO.ResponseDTO;

import java.math.BigDecimal;

public record PedidoItemResponseDTO(int id, int pedidoId, int produtoId, String clienteNome,String produtoNome, int quantidade, BigDecimal precoUnitarioPedido, BigDecimal subtotal) {

}
