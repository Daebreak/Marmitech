package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
