package com.marmitech.Marmitech.Services;
import com.marmitech.Marmitech.Repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public String buscarCategoria(int id){
        return categoriaRepository.findById(id).toString();
    }
    }

