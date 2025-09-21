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

    public String buscarCategoria(int id) {
        return categoriaRepository.findById(id).toString();
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Object save(@Valid Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void delete(Integer id) {
        var delete = findById(id);
        categoriaRepository.delete(delete);
    }

    public Categoria update(Integer id, Categoria dadosNovaCategoria) {
        // 1. Busca a categoria original no banco de dados.
        Categoria categoriaParaAtualizar = findById(id);

        // 2. Verifica se os DADOS NOVOS para 'nome' são válidos antes de atualizar.
        if (dadosNovaCategoria.getNome() != null && !dadosNovaCategoria.getNome().isBlank()) {
            categoriaParaAtualizar.setNome(dadosNovaCategoria.getNome());
        }

        // 3. Verifica se os DADOS NOVOS para 'descricao' são válidos antes de atualizar.
        if (dadosNovaCategoria.getDescricao() != null && !dadosNovaCategoria.getDescricao().isBlank()) {
            categoriaParaAtualizar.setDescricao(dadosNovaCategoria.getDescricao());
        }

        // 4. Salva a entidade atualizada e a retorna.
        return categoriaRepository.save(categoriaParaAtualizar);
    }

}


