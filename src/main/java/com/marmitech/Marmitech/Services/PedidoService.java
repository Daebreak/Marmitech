package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.PedidoRepository;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public Pedido save(Pedido pedido) {
        pedido.setData_pedido( LocalDateTime.now() );
        //Para salvar o id do usuario no banco de dados
        Usuario usuarioExistente = usuarioRepository.findById( pedido.getUsuario().getUsuarioId() )
                .orElseThrow( () -> new RuntimeException( "Usuario nao encontrado" ) );
        pedido.setUsuario( usuarioExistente );

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
