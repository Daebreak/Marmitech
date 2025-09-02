package com.marmitech.Marmitech.DTO.RequestDTO;

import java.math.BigDecimal;

public record ProdutoSaveDTO(String nome, String descricao, String categoria, int estoque, BigDecimal precoUnitario, String sku) {

}
