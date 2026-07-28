package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {
        usuario.setDataCriacao(LocalDate.now());
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
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
            usuarioUpdate.setEmail(usuario.getEmail());
        }
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioUpdate.setSenha(passwordEncoder.encode(usuario.getSenha()));
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
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        return usuario;
    }
}
