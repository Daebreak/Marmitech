package com.marmitech.Marmitech.auth;

import java.util.Optional;

import com.marmitech.Marmitech.Entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Usuario, Long>{
// Não é mais necessário.... Use UsuarioRepository

	//Optional<Usuario> findByEmail(@NotNull @NotBlank(message = "email não pode ser null ou vazio") String email);

}
