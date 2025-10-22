package com.marmitech.Marmitech.DTO.ResponseDTO;

public record ProdutoListaDTO(int id, String nome, String descricao, String categoria, String dataCadastro, Double precoUnitario, int estoque, String sku) {

}