package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClientHttpRequestFactorySettings clientHttpRequestFactorySettings;


    public Pedido save(Pedido pedido) {
        // pega a data e hora fornecida pelo sistema //
        pedido.setData_pedido(LocalDateTime.now());
        // id do pedido precisa ser maior qu e 0 //
        if (pedido.getPedido_id() < 0 ) {
            throw new IllegalArgumentException("ID invalido");
        }
        // id do cliente precias ser  maior que 0 //
        if (pedido.getCliente_id() < 0) {
        throw new IllegalArgumentException("ID invalido");
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
