package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNome(String nome);
    Optional<Usuario> findByNomeAndSenha(String nome, String senha);

    @Query("SELECT u FROM Usuario u WHERE u.cargo = :cargo")
    List<Usuario> getByCargo(@Param("cargo") String cargo);

}