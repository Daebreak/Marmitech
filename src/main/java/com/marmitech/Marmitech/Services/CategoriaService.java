package com.marmitech.Marmitech.Services;
import com.marmitech.Marmitech.Entity.Categoria;
import com.marmitech.Marmitech.Repository.CategoriaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public String buscarCategoria(int id){
        return categoriaRepository.findById(id).toString();
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Object save(@Valid Categoria categoria) {
        return categoriaRepository.save( categoria );
    }
}

