package com.marmitech.Marmitech.Services;
import com.marmitech.Marmitech.Entity.Categoria;
import com.marmitech.Marmitech.Repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;
    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    @DisplayName("Deve retornar todas as categorias")
    void deveRetornarTodasCategorias() {
        Categoria categoria = new Categoria();
        categoria.setNome( "Marmitas");
        categoria.setDescricao( "Tradicionais");
        List<Categoria> listaDeCategoriasFalsas = List.of(categoria);

        // 2. Configura o MOCK
        when(categoriaRepository.findAll()).thenReturn(listaDeCategoriasFalsas);



        List<Categoria> resultado = categoriaService.findAll();


        //  Verifica se o resultado está correto
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Marmitas", resultado.get(0).getNome());

        // Verifica se o mock foi chamado
        verify(categoriaRepository, times(1)).findAll();
    }
    @Test
    void buscarCategoria() {
    }


    @Test
    @DisplayName("Deve salvar uma nova categoria")
    void deveSalvarCategoria() {

        // Cria a categoria que sera enviada  (sem ID)//
        Categoria categoriaParaSalvar = new Categoria();
        categoriaParaSalvar.setId(null);
        categoriaParaSalvar.setNome( "Marmitas");
        categoriaParaSalvar.setDescricao( "Tradicionais");

        //  Crie a categoria como ela deve voltar do banco (com ID)
        Categoria categoriaSalva = new Categoria();
        categoriaSalva.setId(1);
        categoriaSalva.setNome("Marmitas");
        categoriaSalva.setDescricao("Tradicionais");

        // Configura o MOCK: "Quando repository.save for chamado com QUALQUER Categoria,
        //  retorne a categoriaSalva//
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaSalva);

        // Chama o método REAL do serviço//
        // O método retorna Object//
        Object resultado = categoriaService.save(categoriaParaSalvar);


        //  Verifica o resultado//
        assertNotNull(resultado);
        assertInstanceOf(Categoria.class, resultado); // Garante que é uma Categoria
        assertEquals(1, ((Categoria) resultado).getId());
        assertEquals("Marmitas", ((Categoria) resultado).getNome());

        //  Verifica se o mock 'save' foi chamado//
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    @DisplayName("Deve encontrar uma categoria pelo ID com sucesso")
    void deveEncontrarCategoriaPorId() {

        // Cria a categoria falsa
        Categoria categoriaFalsa = new Categoria();
        categoriaFalsa.setId(1);
        categoriaFalsa.setNome("porcoes");
        categoriaFalsa.setDescricao("porcoes individuais");

        // Configura o MOCK: "Quando 'repository.findById' for chamado com ID 1,
        //    retorna um Optional contendo a categoria falsa"
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoriaFalsa));


        //  Chama o método REAL do serviço//
        Categoria resultado = categoriaService.findById(1);


        // Verifica o resultado//
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("porcoes", resultado.getNome());
    }

    @Test
    @DisplayName("Deve deletar uma categoria com sucesso")
    void deletarCategoria() {

        //  Cria a categoria que será deletada
        Categoria categoriaDeletada = new Categoria();
        categoriaDeletada.setNome("Porcoes");
        categoriaDeletada.setDescricao("porcoes individuais");


        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoriaDeletada));

        doNothing().when(categoriaRepository).delete(categoriaDeletada);


        // Chama o método REAL do serviço //
        categoriaService.delete(1);

        // Verifica se os mocks foram chamados na ordem correta
        verify(categoriaRepository, times(1)).findById(1);
        verify(categoriaRepository, times(1)).delete(categoriaDeletada);
    }

    @Test
    @DisplayName("Deve atualizar uma categoria com sucesso (lógica de update)")
    void deveAtualizarCategoria() {

        //  Cria a categoria ORIGINAL (como está no banco)//
        Categoria categoriaOriginal = new Categoria();
        categoriaOriginal.setId(1);
        categoriaOriginal.setNome("Nome original");
        categoriaOriginal.setDescricao("Descricao original");

        // Cria os DADOS NOVOS que sera enviado//
        Categoria dadosNovos = new Categoria();
        dadosNovos.setId(null);
        dadosNovos.setNome("Nova categoria");
        dadosNovos.setDescricao("Nova Descricao");

        //  Cria como a categoria ATUALIZADA deve ficar//
        Categoria categoriaAtualizada = new Categoria();
        categoriaAtualizada.setId(1);
        categoriaAtualizada.setNome("categoria atualizada");
        categoriaAtualizada.setDescricao("descricao atualizada");


        // Mocka o findById//
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(categoriaOriginal));

        // Mocka o 'save' (último passo do método 'update')
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaAtualizada);

        //  Chama o método REAL do serviço
        Categoria resultado = categoriaService.update(1, dadosNovos);

        // Verifica o resultado
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("categoria atualizada", resultado.getNome());
        assertEquals("descricao atualizada", resultado.getDescricao());

        //  Verifica se o save foi chamado
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }
}