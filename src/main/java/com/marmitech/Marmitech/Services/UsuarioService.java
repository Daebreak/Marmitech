package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        usuario.setDataCriacao(LocalDate.now());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void delete(Integer id) {
        var delete = findById(id);
        usuarioRepository.delete(delete);

    }

    public Usuario update(Integer id, Usuario usuario) {
        Usuario usuarioUpdate = findById(id);
        // Para pegar a data e hora automatica
        usuarioUpdate.setDataCriacao(LocalDate.now());
        if (usuario.getNome() != null && !usuario.getNome().isBlank()) {
            usuarioUpdate.setNome(usuario.getNome());
        }
        if (usuario.getEmail() != null && !usuario.getEmail().isBlank()) {
            usuarioUpdate.setEmail(usuarioUpdate.getEmail());
        }
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioUpdate.setSenha(usuario.getSenha());
        }
        if (usuario.getCargo() != null && !usuario.getCargo().isBlank()) {
            usuarioUpdate.setCargo(usuario.getCargo());
        }
        return usuarioRepository.save(usuarioUpdate);
    }

    public List<Usuario> findByCargo(String cargo) {
        return usuarioRepository.getByCargo(cargo);
    }

    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public Usuario login(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));
    }
}
