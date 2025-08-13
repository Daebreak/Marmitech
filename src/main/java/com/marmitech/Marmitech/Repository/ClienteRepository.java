package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Essa interface é a "ponte" com o banco de dados para a entidade ClienteEntity.
// O JpaRepository já vem com várias funções prontas (salvar, buscar, deletar...),
// então não preciso escrever nada aqui
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
