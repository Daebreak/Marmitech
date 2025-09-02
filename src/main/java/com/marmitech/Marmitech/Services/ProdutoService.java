package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.DTO.RequestDTO.ProdutoSaveDTO;
import com.marmitech.Marmitech.DTO.ResponseDTO.ProdutoListaDTO;
import com.marmitech.Marmitech.Entity.Produto;
import com.marmitech.Marmitech.Mapper.RequestMapper.SaveProdutoMapping;
import com.marmitech.Marmitech.Mapper.ResponseMapper.ProdutoListaMapper;
import com.marmitech.Marmitech.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoListaDTO save(ProdutoSaveDTO produto) {
        Produto novoProduto = SaveProdutoMapping.toEntity(produto);

        novoProduto.setDataCadastro( LocalDate.now().toString() );
        
        Produto salvoProduto = produtoRepository.save( novoProduto );

        return ProdutoListaMapper.toDto(salvoProduto); 
    }

    public List<ProdutoListaDTO> findAll() {
        return produtoRepository.findAll()
        .stream()
        .map(ProdutoListaMapper::toDto)
        .toList();
    }

    public Produto findById(Integer id) {
        return produtoRepository.findById( id ).orElseThrow( RuntimeException::new );
    }

    public void delete(Integer id) {
        var delete = findById( id );
        produtoRepository.delete( delete );
    }

    public Produto update(Integer id, Produto produto) {
        Produto produtoUpdate = findById( id );
        produtoUpdate.setDataCadastro( LocalDate.now().toString() );
    
        return produtoRepository.save( produtoUpdate );
    }
}
