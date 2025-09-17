package com.marmitech.Marmitech.Mapper.ResponseMapper;

import com.marmitech.Marmitech.DTO.ResponseDTO.ProdutoListaDTO;
import com.marmitech.Marmitech.Entity.Produto;

public class ProdutoListaMapper {
    public static ProdutoListaDTO toDto(Produto produto) {
        return new ProdutoListaDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(), produto.getCategoria(),
                produto.getDataCadastro(), produto.getPrecoUnitario(), produto.getEstoque(),
                produto.getSku() );
    }
}