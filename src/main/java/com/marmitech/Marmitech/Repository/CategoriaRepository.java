package com.marmitech.Marmitech.Repository;
import com.marmitech.Marmitech.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CategoriaRepository extends JpaRepository <Categoria,Integer> {
    List<Categoria>findById(int id);

}
