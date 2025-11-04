package com.marmitech.Marmitech.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Usuario, Long>{
// Não é mais necessário.... Use UsuarioRepository

	//public Optional<Usuario> findByUsername(String login);
	
}
