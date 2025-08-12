package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public Pedido save(Pedido pedido) {
        pedido.setData_pedido( LocalDateTime.now() );
        return pedidoRepository.save( pedido );
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        return pedidoRepository.findById( id ).orElseThrow( RuntimeException::new );
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
