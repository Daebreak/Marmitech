package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
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

    public void login(String nome, String senha) {
        boolean existe = usuarioRepository.findByNomeAndSenha( nome, senha ).isPresent();

        if (!existe) {
            throw new RuntimeException();
        }
    }
}
