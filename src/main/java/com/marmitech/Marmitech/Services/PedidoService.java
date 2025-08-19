package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.HistoricoCompra;
import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Entity.Produto;
import com.marmitech.Marmitech.Repository.ClienteRepository;
import com.marmitech.Marmitech.Repository.PedidoRepository;
import com.marmitech.Marmitech.Repository.UsuarioRepository;

import jakarta.transaction.Transactional;

import com.marmitech.Marmitech.Repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Pedido save(Pedido pedido) {
        List<PedidoItem> itens = new ArrayList<>(pedido.getPedidoItems());
        pedido.getPedidoItems().clear();
        pedido.setData_pedido(LocalDate.now().toString());
        
        for (HistoricoCompra hist : pedido.getHistoricos()) {
            hist.setPedido(pedido);
        }

    if (pedido.getUsuario() != null && pedido.getUsuario().getId() < 0) {
        var usuario = usuarioRepository.findById(pedido.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        pedido.setUsuario(usuario);
    }

    if (pedido.getCliente() != null && pedido.getCliente().getId() < 0) {
        var cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        pedido.setCliente(cliente);
    }

        for (PedidoItem item : itens) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));

            item.setProduto(produto);
            
            pedido.addItem(item);
        }

        if (pedido.getData_pedido() == null) {
            throw new IllegalArgumentException("Data do pedido não pode ser nulo");
        }
        if (pedido.getValor_total() == null){
            throw new IllegalArgumentException("O valor não pode ser nulo ");
        }
        if (pedido.getStatus() == null){
            throw new IllegalArgumentException("Status nao pode ser nulo");
        }
        if (pedido.getEndereco_entrega() == null){
            
        }


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

    @Transactional
    public Pedido update(Integer id, Pedido pedido) {
        Pedido pedidoUpdate = findById( id );
        return pedidoRepository.save( pedidoUpdate );
    }

    @Transactional
    public void delete(Integer id) {
        var delete = findById( id );
        pedidoRepository.delete( delete );
        if ( id < 0){
            throw new IllegalArgumentException("ID DO PEDIDO INVALIDO");
        }

    }
}
