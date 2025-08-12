package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
