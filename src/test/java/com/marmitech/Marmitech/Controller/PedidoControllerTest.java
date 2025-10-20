package com.marmitech.Marmitech.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marmitech.Marmitech.DTO.ResponseDTO.PedidoItemResponseDTO;
import com.marmitech.Marmitech.DTO.ResponseDTO.PedidoResponseDTO;
import com.marmitech.Marmitech.Entity.*;
import com.marmitech.Marmitech.Services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PedidoService pedidoService;

    @Autowired
    ObjectMapper objectMapper;

    Pedido pedido;
    PedidoResponseDTO pedidoResponseDTO;


    @BeforeEach
    void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId( 1 );
        usuario.setNome( "Caixa Teste" );

        Cliente cliente = new Cliente();
        cliente.setId( 1 );
        cliente.setNome( "Cliente Teste" );

        Produto produto = new Produto();
        produto.setId( 1 );
        produto.setNome( "Refrigerante" );

        // PedidoItem Response DTO (simplificado para o mock)
        PedidoItemResponseDTO itemDto = new PedidoItemResponseDTO( 1, 1, 1, "Cliente Teste", "Refrigerante", 2, 10.00, 20.00 );

        // Pedido principal para o Service (Entidade)
        pedido = new Pedido();
        pedido.setId( 1 );
        pedido.setValorTotal( 20.00 );
        pedido.setStatus( "FILA" );
        pedido.setEnderecoEntrega( "Rua Teste, 123" );
        pedido.setDataPedido( "2025-10-15" );
        pedido.setUsuario( usuario );
        pedido.setCliente( cliente );
        pedido.setPedidoItems( Collections.emptySet() );
        pedido.setHistoricos( Collections.emptyList() );

        // Pedido Response DTO para o Controller
        pedidoResponseDTO = new PedidoResponseDTO( 1, "Cliente Teste", "FILA", "Rua Teste, 123", Set.of( itemDto ), 20.00, "2025-10-12" );
    }

    @Test
    @DisplayName("save - Deve criar um novo pedido com status CREATED")
    void cenario01() throws Exception {
        Mockito.when( pedidoService.save( any( Pedido.class ) ) ).thenReturn( pedido );
        String pedidoJson = objectMapper.writeValueAsString( pedido );

        mockMvc.perform( post( "/api/pedido/save" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( pedidoJson ) )
                .andDo( print() )
                .andExpect( status().isCreated() )
                .andExpect( jsonPath( "$.id" ).value( 1 ) )
                .andExpect( jsonPath( "$.status" ).value( "FILA" ) );
    }

    @Test
    @DisplayName("findAll - Deve retornar todos os pedidos com status OK")
    void cenario02() throws Exception {
        String result = objectMapper.writeValueAsString( List.of( pedidoResponseDTO ) );
        Mockito.when( pedidoService.findAll() ).thenReturn( List.of( pedidoResponseDTO ) );

        mockMvc.perform( get( "/api/pedido" ) )
                .andDo( print() )
                .andExpect( status().isOk() )
                .andExpect( content().json( result ) );
    }

    @Test
    @DisplayName("findById/{id} - Deve retornar um pedido por ID com status OK")
    void cenario03() throws Exception {
        Mockito.when( pedidoService.findById( 1 ) ).thenReturn( pedido );

        mockMvc.perform( get( "/api/pedido/findById/1" ) )
                .andDo( print() )
                .andExpect( status().isOk() );

        verify( pedidoService, Mockito.times( 1 ) ).findById( 1 );
    }

    @Test
    @DisplayName("delete/{id} - Deve deletar um pedido com status OK")
    void cenario04() throws Exception {
        Mockito.doNothing().when( pedidoService ).delete( 1 );

        mockMvc.perform( delete( "/api/pedido/delete/1" ) )
                .andDo( print() )
                .andExpect( status().isOk() );

        verify( pedidoService, Mockito.times( 1 ) ).delete( 1 );
    }

    @Test
    @DisplayName("findById/{id} - Deve retornar BAD_REQUEST se o ID for inválido")
    void cenario05() throws Exception {
        Mockito.when( pedidoService.findById( anyInt() ) ).thenThrow( new RuntimeException( "ID DO PEDIDO INVALIDO" ) );

        mockMvc.perform( get( "/api/pedido/findById/99" ) )
                .andDo( print() )
                .andExpect( status().isBadRequest() );
    }

    @Test
    @DisplayName("findByStatus - Deve retornar lista de pedidos por Status com status FOUND")
    void cenario06() throws Exception {
        Mockito.when( pedidoService.findByStatus( "FILA" ) ).thenReturn( List.of( pedido ) );

        mockMvc.perform( get( "/api/pedido/findByStatus" )
                        .param( "status", "FILA" ) )
                .andDo( print() )
                .andExpect( status().isFound() )
                .andExpect( jsonPath( "$[0].id" ).value( 1 ) );

        Mockito.verify( pedidoService, Mockito.times( 1 ) ).findByStatus( "FILA" );
    }

    @Test
    @DisplayName("findByStatus - Deve retornar BAD_REQUEST se o service lançar exceção")
    void cenario07() throws Exception {
        Mockito.when( pedidoService.findByStatus( anyString() ) ).thenThrow( new RuntimeException( "STATUS DO PEDIDO INVALIDO" ) );

        mockMvc.perform( get( "/api/pedido/findByStatus" )
                        .param( "status", "INVALIDO" ) )
                .andDo( print() )
                .andExpect( status().isBadRequest() )
                .andExpect( content().string( "STATUS DO PEDIDO INVALIDO" ) );
    }

    @Test
    @DisplayName("findByProdutoNome - Deve retornar lista de pedidos por nome do Produto com status FOUND")
    void cenario08() throws Exception {
        Mockito.when( pedidoService.findByProdutoNome( "Refrigerante" ) ).thenReturn( List.of( pedido ) );

        mockMvc.perform( get( "/api/pedido/findByProdutoNome" )
                        .param( "nomeProduto", "Refrigerante" ) )
                .andDo( print() )
                .andExpect( status().isFound() )
                .andExpect( jsonPath( "$[0].id" ).value( 1 ) );

        Mockito.verify( pedidoService, Mockito.times( 1 ) ).findByProdutoNome( "Refrigerante" );
    }

    @Test
    @DisplayName("findByProdutoNome - Deve retornar BAD_REQUEST se o service lançar exceção")
    void cenario09() throws Exception {
        Mockito.when( pedidoService.findByProdutoNome( anyString() ) ).thenThrow( new RuntimeException( "NOME DO PRODUTO INVÁLIDO" ) );

        mockMvc.perform( get( "/api/pedido/findByProdutoNome" )
                        .param( "nomeProduto", "Produto Invalido" ) )
                .andDo( print() )
                .andExpect( status().isBadRequest() )
                .andExpect( content().string( "NOME DO PRODUTO INVÁLIDO" ) );
    }

    @Test
    @DisplayName("findByProduto - Deve retornar lista de pedidos por ID de Produto com status FOUND")
    void cenario10() throws Exception {
        Mockito.when( pedidoService.findByProduto( 1 ) ).thenReturn( List.of( pedido ) );

        mockMvc.perform( get( "/api/pedido/findByProduto" )
                        .param( "produtoId", String.valueOf( 1 ) ) )
                .andDo( print() )
                .andExpect( status().isFound() )
                .andExpect( jsonPath( "$[0].id" ).value( 1 ) );

        Mockito.verify( pedidoService, Mockito.times( 1 ) ).findByProduto( 1 );
    }

    @Test
    @DisplayName("findByProduto - Deve retornar BAD_REQUEST se o service lançar exceção")
    void cenario11() throws Exception {
        Mockito.when( pedidoService.findByProduto( 99 ) ).thenThrow( new RuntimeException( "PRODUTO NÃO ENCONTRADO" ) );

        mockMvc.perform( get( "/api/pedido/findByProduto" )
                        .param( "produtoId", String.valueOf( 99 ) ) )
                .andDo( print() )
                .andExpect( status().isBadRequest() )
                .andExpect( content().string( "PRODUTO NÃO ENCONTRADO" ) );
    }

    @Test
    @DisplayName("update/{id} - Deve atualizar o Pedido com status OK")
    void cenario12() throws Exception {
        Pedido pedidoAtualizado = pedido;
        pedidoAtualizado.setStatus( "ENVIADO" );

        Mockito.when( pedidoService.update( eq( 1 ), any( Pedido.class ) ) ).thenReturn( pedidoAtualizado );

        Pedido pedidoUpdate = new Pedido();
        pedidoUpdate.setStatus( "ENVIADO" );
        String pedidoJson = objectMapper.writeValueAsString( pedidoUpdate );

        mockMvc.perform( put( "/api/pedido/update/1" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( pedidoJson ) )
                .andDo( print() )
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.id" ).value( 1 ) )
                .andExpect( jsonPath( "$.status" ).value( "ENVIADO" ) );

        Mockito.verify( pedidoService, Mockito.times( 1 ) ).update( eq( 1 ), any( Pedido.class ) );
    }

    @Test
    @DisplayName("update/{id} - Deve retornar BAD_REQUEST se o Pedido não for encontrado")
    void cenario13() throws Exception {
        Mockito.when( pedidoService.update( eq( 99 ), any( Pedido.class ) ) ).thenThrow( new RuntimeException( "Pedido com ID 99 não encontrado" ) );

        Pedido pedidoUpdate = new Pedido();
        pedidoUpdate.setStatus( "ENVIADO" );
        String pedidoJson = objectMapper.writeValueAsString( pedidoUpdate );

        mockMvc.perform( put( "/api/pedido/update/99" )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( pedidoJson ) )
                .andDo( print() )
                .andExpect( status().isBadRequest() )
                .andExpect( content().string( "Pedido com ID 99 não encontrado" ) );
    }

}