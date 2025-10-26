package com.marmitech.Marmitech.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmitech.Marmitech.DTO.RequestDTO.ProdutoSaveDTO;
import com.marmitech.Marmitech.DTO.ResponseDTO.ProdutoListaDTO;
import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Entity.Produto;
import com.marmitech.Marmitech.Services.ProdutoService;


@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    ProdutoService produtoService;
    

    @Test
    @DisplayName("Teste: Find All Produtos Controller")
    public void test22() throws Exception {

        ProdutoListaDTO produto1 = new ProdutoListaDTO(1, "Produto A", "Descrição A", "Categoria A", "Hoje", 10.00, 10, "111");
        ProdutoListaDTO produto2 = new ProdutoListaDTO(2, "Produto B", "Descrição B", "Categoria B", "Hoje", 20.00, 20, "222");
        List<ProdutoListaDTO> produtos = Arrays.asList(produto1, produto2);

        given(produtoService.findAll()).willReturn(produtos);

        mockMvc.perform(get("/api/produto/findAll"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].nome").value("Produto B"));
    }

    @Test
    @DisplayName("Teste: Find By Id Produto Controller")
    public void test23() throws Exception {
        Produto produto = new Produto();
        produto.setId(31);

        given(produtoService.findById(1)).willReturn(produto);

        mockMvc.perform(get("/api/produto/findById/{id}", 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(31));
    }

    @Test
    @DisplayName("Teste: Save Produto Controller")
    public void test24() throws Exception {
        ProdutoListaDTO produto1 = new ProdutoListaDTO(1, "Produto A", "Descrição A", "Categoria A", "Hoje", 10.00, 10, "111");
        ProdutoSaveDTO produto2 = new ProdutoSaveDTO("Produto A", "Descrição A", "Categoria A", 10, 10.00, "111");

        given(produtoService.save(any(ProdutoSaveDTO.class))).willReturn(produto1);

        mockMvc.perform(post("/api/produto/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produto2)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Produto A"));  
    }

    @Test
    @DisplayName("Teste: Delete Produto Controller")
    public void test25() throws Exception {
        doNothing().when(produtoService).delete(1);

        mockMvc.perform(delete("/api/produto/delete/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Teste: Update Produto Controller")
    public void test26() throws Exception {
        Set<PedidoItem> pedidoItem = new HashSet<>();

        Produto produto = new Produto(1, "111", "Produto A", "Descrição A", 10.00, 10, "null", "hoje", pedidoItem);

        given(produtoService.update(1, produto)).willReturn(produto);

        mockMvc.perform(put("/api/produto/update/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
