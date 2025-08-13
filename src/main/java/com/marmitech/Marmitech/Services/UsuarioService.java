package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        //Para pegar a data e hora automatica
        usuario.setData_criacao( LocalDateTime.now() );
        return usuarioRepository.save( usuario );
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById( id ).orElseThrow( RuntimeException::new );
    }

    public void delete(Integer id) {
        var delete = findById( id );
        usuarioRepository.delete( delete );
    }

    public Usuario update(Integer id, Usuario usuario) {
        Usuario usuarioUpdate = findById( id );
        //Para pegar a data e hora automatica
        usuarioUpdate.setData_criacao( LocalDateTime.now() );
        if (usuario.getNome() != null && !usuario.getNome().isBlank()) {
            usuarioUpdate.setNome( usuario.getNome() );
        }
        if (usuario.getEmail() != null && !usuario.getEmail().isBlank()) {
            usuarioUpdate.setEmail( usuarioUpdate.getEmail() );
        }
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioUpdate.setSenha( usuario.getSenha() );
        }
        if (usuario.getCargo() != null && !usuario.getCargo().isBlank()) {
            usuarioUpdate.setCargo( usuario.getCargo() );
        }
        return usuarioRepository.save( usuarioUpdate );
    }
}
