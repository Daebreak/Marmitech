package com.marmitech.Marmitech.DTO.ResponseDTO;

import java.math.BigDecimal;

public record PedidoItemResponseDTO(String produtoNome, int quantidade, BigDecimal precoUnitarioPedido, BigDecimal subtotal) {

}
