package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Entity.Produto;
import com.marmitech.Marmitech.Repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto save(Produto produto) {
        produto.setData_cadastro( LocalDate.now().toString() );
        return produtoRepository.save( produto );
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
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
        produtoUpdate.setData_cadastro( LocalDateTime.now().toString() );
        
        if (produto.getNome() != null || produto.getNome().isBlank()) {
            produtoUpdate.setNome( produto.getNome() );
        }
        if (produto.getDescricao() != null || produto.getDescricao().isBlank()) {
            produtoUpdate.setDescricao( produto.getDescricao() );
        }
        if (produto.getPreco_unitario() > 0) {
            produtoUpdate.setPreco_unitario( produto.getPreco_unitario() );
        }
        if (produto.getCategoria() != null || produto.getCategoria().isBlank()) {
            produtoUpdate.setCategoria( produto.getCategoria() );
        }
        return produtoRepository.save( produtoUpdate );

    }
}
