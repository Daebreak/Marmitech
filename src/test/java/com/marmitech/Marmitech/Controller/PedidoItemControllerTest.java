package com.marmitech.Marmitech.Controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmitech.Marmitech.DTO.ResponseDTO.PedidoItemResponseDTO;
import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Services.PedidoItemService;

@WebMvcTest(PedidoItemController.class)
public class PedidoItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PedidoItemService pedidoItemService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Teste: Find All PedidoItems Controller")
    void test27() throws Exception {
        PedidoItemResponseDTO pedidoItem1 = new PedidoItemResponseDTO(1, 1, 1, "Cliente'", "Produto", 19, new BigDecimal(10.00), new BigDecimal(10.00));
    
        PedidoItemResponseDTO pedidoItem2 = new PedidoItemResponseDTO(2, 2, 2, "Cliente B", "Produto B", 29, new BigDecimal(20.00), new BigDecimal(20.00));
    
        List<PedidoItemResponseDTO> pedidoItems = Arrays.asList(pedidoItem1, pedidoItem2);

        given(pedidoItemService.findAll()).willReturn(pedidoItems);

        mockMvc.perform(get("/pedidoItem/findAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].clienteNome").value("Cliente B"))
                .andDo(print());
    }

    @Test
    @DisplayName("Teste: Save PedidoItem Controller")
    void test28() throws Exception {
        PedidoItemResponseDTO pedidoItem = new PedidoItemResponseDTO(1, 1, 1, "Cliente A", "Produto A", 19, new BigDecimal(10.00), new BigDecimal(10.00));

        PedidoItem pedidoItem1 = new PedidoItem();
        pedidoItem1.setId(1);


        given(pedidoItemService.save(any(PedidoItemResponseDTO.class))).willReturn(pedidoItem1);

        mockMvc.perform(post("/pedidoItem/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedidoItem)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    @DisplayName("Teste: Find By Id PedidoItem Controller")
    void test29() throws Exception {
        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setId(1);
        
        given(pedidoItemService.findById(1)).willReturn(pedidoItem);

        mockMvc.perform(get("/pedidoItem/findById/{pedidoItemId}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    @DisplayName("Teste: Delete PedidoItem Controller")
    void test30()  throws Exception {
       
        given(pedidoItemService.delete(1)).willReturn("Linha deleteda da tabela.");   

        mockMvc.perform(delete("/pedidoItem/delete/{pedidoItemId}", 1))
                .andExpect(status().isCreated())
                .andExpect(content().string("Linha deleteda da tabela."))
                .andDo(print());
    }

    @Test
    @DisplayName("Teste: Update PedidoItem Controller")
    void test31() throws Exception {
        PedidoItemResponseDTO atualizadoPedidoItem = new PedidoItemResponseDTO(1, 1, 1, "Cliente A", "Produto A", 19, new BigDecimal(10.00), new BigDecimal(10.00));    

        PedidoItem pedidoItem = new PedidoItem();
        pedidoItem.setId(1);

        given(pedidoItemService.update(atualizadoPedidoItem, 1)).willReturn(pedidoItem);

        mockMvc.perform(put("/pedidoItem/update/{pedidoItemId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atualizadoPedidoItem)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

}
