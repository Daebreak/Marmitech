package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
}
