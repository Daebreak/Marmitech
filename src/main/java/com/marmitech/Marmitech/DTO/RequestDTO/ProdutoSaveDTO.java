package com.marmitech.Marmitech.DTO.RequestDTO;

public record ProdutoSaveDTO(String nome, String descricao, String categoria, int estoque, Double precoUnitario, String sku) {
}