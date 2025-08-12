package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
}
