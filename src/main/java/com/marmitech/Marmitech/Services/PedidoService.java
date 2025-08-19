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
        // id do pedido precisa ser maior que 0
        if (pedido.getId() < 0 ) {
            throw new IllegalArgumentException("ID invalido");
        }
        if (pedido.getDataPedido() == null) {
            throw new IllegalArgumentException("Data do pedido não pode ser nulo");
        }
        if (pedido.getValorTotal() == null){
            throw new IllegalArgumentException("O valor não pode ser nulo ");
        }
        if (pedido.getStatus() == null){
            throw new IllegalArgumentException("Status nao pode ser nulo");
        }

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
        pedido.setDataPedido( LocalDateTime.now().toString() );
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
         List<Pedido> listaPedidos = pedidoRepository.findAll();
        if (listaPedidos == null){
            throw new IllegalArgumentException("Nao ha produtos para exibir");
        }
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        if (id < 0){
            throw new IllegalArgumentException("ID DO PEDIDO INVALIDO");
        }
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
        if (id < 0){
            throw new IllegalArgumentException("ID DO PEDIDO INVALIDO");
        }
        return pedidoRepository.save( pedidoUpdate );
    }

    public void delete(Integer id) {
        var delete = findById( id );
        pedidoRepository.delete( delete );
        if ( id < 0){
            throw new IllegalArgumentException("ID DO PEDIDO INVALIDO");
        }

    }
}
