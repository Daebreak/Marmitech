package com.marmitech.Marmitech.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmitech.Marmitech.Entity.Categoria;
import com.marmitech.Marmitech.Services.CategoriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.ArgumentMatchers.eq;


@WebMvcTest(CategoriaController.class)
@ActiveProfiles("test")
class CategoriaControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    CategoriaService categoriaService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
   @DisplayName("Deve retornar a lista de categorias com sucesso")
    void buscarcategorias() throws Exception {
       Categoria categoria1= new Categoria();
       Categoria categoria2 = new Categoria();
       categoria1.setNome("Marmitex");
       categoria1.setDescricao("Marmita Grande");
       categoria2.setNome("Porcao torresmo");
       categoria2.setDescricao("Porcao torresmo 350g");

       List<Categoria> listadeCategorias =  Arrays.asList(categoria1,categoria2);

       when(categoriaService.findAll()).thenReturn(listadeCategorias);

       mockMvc.perform(get("/api/categoria/findAll"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].nome", is("Marmitex")))
               .andExpect(jsonPath("$[1].nome", is("Porcao torresmo")));

   }
   @Test
   @DisplayName("deve salvar uma categoria")
   void salvarcategoria() throws Exception{
        Categoria  novaCategoria = new Categoria();
        novaCategoria.setNome("Marmitas");
        novaCategoria.setDescricao("Marmitas variadas");

        Categoria categoriaSalva = new Categoria();
        categoriaSalva.setId(1);
        categoriaSalva.setNome("Marmitas");
        categoriaSalva.setDescricao("Marmitas variadas");

        when(categoriaService.save(any(Categoria.class))).thenReturn(categoriaSalva);

        String jsonenviado = objectMapper.writeValueAsString(categoriaSalva);

        mockMvc.perform(post("/api/categoria/save").contentType(MediaType.APPLICATION_JSON)
                .content(jsonenviado)).andExpect(status().isCreated()).andExpect(jsonPath("$.id",is(1))).andExpect(jsonPath("$.nome",is("Marmitas"))).andExpect(jsonPath("$.descricao",is("Marmitas variadas")));

   }
    @Test
    @DisplayName("Deve deletar uma categoria com sucesso")
    void DeletarCategoriaComSucesso() throws Exception {

        Integer idParaDeletar = Math.toIntExact(1);

        doNothing().when(categoriaService).delete(idParaDeletar);

        mockMvc.perform(delete("/api/categoria/delete/{id}", idParaDeletar))

                .andExpect(status().isOk());



        verify(categoriaService, times(1)).delete(idParaDeletar);
    }

    @Test
    @DisplayName("Deve atualizar uma categoria com sucesso")
    void deveAtualizarCategoriaComSucesso() throws Exception {

        Integer idParaAtualizar = 1;


        Categoria dadosParaAtualizar = new Categoria();
        dadosParaAtualizar.setNome("Marmitex Atualizada");
        dadosParaAtualizar.setDescricao("Descricao Atualizada");


        Categoria categoriaAtualizadaRetornada = new Categoria();
        categoriaAtualizadaRetornada.setId(idParaAtualizar);
        categoriaAtualizadaRetornada.setNome("Marmitex Atualizada");
        categoriaAtualizadaRetornada.setDescricao("Descricao Atualizada");

        when(categoriaService.update(eq(idParaAtualizar), any(Categoria.class)))
                .thenReturn(categoriaAtualizadaRetornada);

        String jsonParaEnviar = objectMapper.writeValueAsString(dadosParaAtualizar);

        mockMvc.perform(put("/api/categoria/update/{id}", idParaAtualizar)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParaEnviar))



                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", is(idParaAtualizar.intValue())))
                .andExpect(jsonPath("$.nome", is("Marmitex Atualizada")))
                .andExpect(jsonPath("$.descricao", is("Descricao Atualizada")));
    }
}