package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.*;

public interface ClienteRepositoryTest extends JpaRepository<Cliente, Long> {

}