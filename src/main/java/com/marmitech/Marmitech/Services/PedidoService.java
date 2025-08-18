package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Entity.Produto;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.PedidoRepository;
import com.marmitech.Marmitech.Repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pedido save(Pedido pedido) {
        List<PedidoItem> itens = new ArrayList<>(pedido.getPedidoItems());
        pedido.getPedidoItems().clear();
        pedido.setData_pedido( LocalDateTime.now().toString() );
        //Para salvar o id do usuario no banco de dados
        Usuario usuarioExistente = usuarioRepository.findById( pedido.getUsuario().getUsuarioId() )
                .orElseThrow( () -> new RuntimeException( "Usuario nao encontrado" ) );
        pedido.setUsuario( usuarioExistente );

        for (PedidoItem item : itens) {
        Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));

            item.setProduto(produto);

            pedido.addItem(item);
        }

        pedido.setData_pedido(LocalDate.now().toString());
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        return pedidoRepository.findById( id ).orElseThrow( RuntimeException::new );
    }

    public List<Pedido> findByStatus(String status) {
        return pedidoRepository.findByStatus( status );
    }

    public List<Pedido> findByProduto(int produtoId) {
        Produto produto = new Produto();
        produto.setId(produtoId);

        return pedidoRepository.findByPedidoItemsProduto( produto );
    }

    public List<Pedido> findByProdutoNome(String nomeProduto) {
        return pedidoRepository.findByPedidoItemsProdutoNome( nomeProduto );
    }

    public Pedido update(Integer id, Pedido pedido) {
        Pedido pedidoUpdate = findById( id );
        return pedidoRepository.save( pedidoUpdate );
    }

    public void delete(Integer id) {
        var delete = findById( id );
        pedidoRepository.delete( delete );
    }
}
