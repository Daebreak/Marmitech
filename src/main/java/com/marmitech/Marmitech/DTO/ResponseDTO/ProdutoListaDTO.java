package com.marmitech.Marmitech.DTO.ResponseDTO;

import java.math.BigDecimal;

public record ProdutoListaDTO(int id, String nome, String descricao, String categoria, String dataCadastro, BigDecimal precoUnitario, int estoque, String sku) {

}