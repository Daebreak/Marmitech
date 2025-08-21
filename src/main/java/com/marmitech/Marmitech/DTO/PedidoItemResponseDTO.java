package com.marmitech.Marmitech.DTO;

import java.math.BigDecimal;

public record PedidoItemResponseDTO(String produtoNome, int quantidade, BigDecimal precoUnitarioPedido) {

}
