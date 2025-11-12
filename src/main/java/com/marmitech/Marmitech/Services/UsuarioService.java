package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

//     @Autowired
//     private final BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
     private final PasswordEncoder passwordEncoder;

     public Usuario save(Usuario usuario) {
        //Para pegar a data e hora automatica
        usuario.setData_criacao( LocalDate.now() );

         usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
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
        usuarioUpdate.setData_criacao( LocalDate.now() );
        if (usuario.getNome() != null && !usuario.getNome().isBlank()) {
            usuarioUpdate.setNome( usuario.getNome() );
        }
        if (usuario.getEmail() != null && !usuario.getEmail().isBlank()) {
            usuarioUpdate.setEmail( usuarioUpdate.getEmail() );
        }
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioUpdate.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }
        if (usuario.getCargo() != null && !usuario.getCargo().isBlank()) {
            usuarioUpdate.setCargo( usuario.getCargo() );
        }
//        if(!usuario.getPassword().equals("")) {
//            String senhaCriptografada = bCryptPasswordEncoder.encode(usuario.getSenha());
//           usuario.setSenha(senhaCriptografada);
//
//        }
        return usuarioRepository.save( usuarioUpdate );
    }

    public void login(String nome) throws RuntimeException {
        Optional<Usuario> usuarioOPT = usuarioRepository.findById(Integer.parseInt(nome));

        if (usuarioOPT.isEmpty()) {
            throw new RuntimeException( "Usuario ou senha invalidos" );
        }
        Usuario usuario = usuarioOPT.get();


        if (usuario.getCargo().equalsIgnoreCase( "Caixa" )) {
            System.out.println( "Mudando para tela de caixa" );
        }
        if (usuario.getCargo().equalsIgnoreCase( "Cozinha" )) {
            System.out.println( "Mudando para a tela de cozinha" );
        }


    }


    public List<Usuario> findByCargo(String cargo) {
        return usuarioRepository.getByCargo( cargo );
    }

    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNome( nome );
    }


}
