package com.marmitech.Marmitech.Repository;

import com.marmitech.Marmitech.Model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
