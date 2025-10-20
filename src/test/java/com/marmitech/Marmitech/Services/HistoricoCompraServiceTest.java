package com.marmitech.Marmitech.Services;

import com.marmitech.Marmitech.Entity.HistoricoCompra;
import com.marmitech.Marmitech.Repository.HistoricoCompraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoricoCompraServiceTest {

    @InjectMocks
    HistoricoCompraService historicoCompraService;

    @Mock
    HistoricoCompraRepository historicoCompraRepository;

    HistoricoCompra historicoCompra;

    @BeforeEach
    void setUp() {
        historicoCompra = new HistoricoCompra();
        historicoCompra.setId( 1 );
        historicoCompra.setDataEvento( "2025-10-12" );
        historicoCompra.setTipoEvento( "CRIADO" );
        historicoCompra.setDescricao( "Pedido Criado" );
    }

    @Test
    @DisplayName("Deve salvar um HistoricoCompra com sucesso")
    void cenario01() {
        when( historicoCompraRepository.save( any( HistoricoCompra.class ) ) ).thenReturn( historicoCompra );

        HistoricoCompra result = historicoCompraService.save( historicoCompra );

        assertNotNull( result );
        assertEquals( 1, result.getId() );
        verify( historicoCompraRepository, times( 1 ) ).save( historicoCompra );
    }

    @Test
    @DisplayName("Deve buscar todos os HistoricoCompra com sucesso")
    void cenario02() {

        HistoricoCompra historico2 = new HistoricoCompra();
        historico2.setId( 2 );
        when( historicoCompraRepository.findAll() ).thenReturn( List.of( historicoCompra, historico2 ) );

        List<HistoricoCompra> result = historicoCompraService.findAll();

        assertNotNull( result );
        assertEquals( 2, result.size() );
        verify( historicoCompraRepository, times( 1 ) ).findAll();
    }

    @Test
    @DisplayName("Deve buscar HistoricoCompra por DataEvento com sucesso")
    void cenario03() {
        when( historicoCompraRepository.findByDataEvento( "2025-10-12" ) ).thenReturn( List.of( historicoCompra ) );

        List<HistoricoCompra> result = historicoCompraService.findByDataEvento( "2025-10-12" );

        assertNotNull( result );
        assertEquals( 1, result.size() );
        verify( historicoCompraRepository, times( 1 ) ).findByDataEvento( "2025-10-12" );
    }

    @Test
    @DisplayName("Deve buscar um HistoricoCompra por ID com sucesso")
    void cenario04() {
        when( historicoCompraRepository.findById( 1 ) ).thenReturn( Optional.of( historicoCompra ) );

        HistoricoCompra result = historicoCompraService.findById( 1 );

        assertNotNull( result );
        assertEquals( 1, result.getId() );
        verify( historicoCompraRepository, times( 1 ) ).findById( 1 );
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar HistoricoCompra por ID inexistente")
    void cenario05() {
        when( historicoCompraRepository.findById( 99 ) ).thenReturn( Optional.empty() );

        assertThrows( RuntimeException.class, () -> historicoCompraService.findById( 99 ) );
        verify( historicoCompraRepository, times( 1 ) ).findById( 99 );
    }

    @Test
    @DisplayName("update - Deve manter valores antigos se os novos forem nulos/vazios")
    void cenario06() {
        HistoricoCompra antigoHistorico = new HistoricoCompra();
        antigoHistorico.setId( 1 );
        antigoHistorico.setDataEvento( "2025-10-13" );
        antigoHistorico.setTipoEvento( "ANTIGO" );
        antigoHistorico.setDescricao( "Descricao Antiga" );

        HistoricoCompra novoHistoricoComInvalidos = new HistoricoCompra();
        novoHistoricoComInvalidos.setDataEvento( null );
        novoHistoricoComInvalidos.setTipoEvento( "" );
        novoHistoricoComInvalidos.setDescricao( "Nova Descricao Valida" );

        when( historicoCompraRepository.findById( 1 ) ).thenReturn( Optional.of( antigoHistorico ) );

        // Mock para save: deve retornar o objeto modificado (o 'antigoHistorico')
        when( historicoCompraRepository.save( any( HistoricoCompra.class ) ) ).thenAnswer( invocation -> invocation.getArgument( 0 ) );

        HistoricoCompra result = historicoCompraService.update( novoHistoricoComInvalidos, 1 );

        assertNotNull( result );
        assertEquals( 1, result.getId() );

        // Verifica se os campos foram atualizados corretamente no objeto retornado
        assertEquals( "2025-10-13", result.getDataEvento(), "DataEvento não deve mudar (era nulo)" );
        assertEquals( "ANTIGO", result.getTipoEvento(), "TipoEvento não deve mudar (era vazio)" );
        assertEquals( "Nova Descricao Valida", result.getDescricao(), "Descricao deve ser atualizada" );

        verify( historicoCompraRepository, times( 1 ) ).findById( 1 );
        verify( historicoCompraRepository, times( 1 ) ).save( antigoHistorico );
    }

    @Test
    @DisplayName("delete - Deve deletar um HistoricoCompra por ID")
    void cenario07() {
        doNothing().when( historicoCompraRepository ).deleteById( 1 );

        String result = historicoCompraService.delete( 1 );

        assertEquals( "Historico deletado com sucesso!", result );
        verify( historicoCompraRepository, times( 1 ) ).deleteById( 1 );
    }

    @Test
    @DisplayName("findById - Deve lançar exceção se ID for negativo")
    void cenario08() {
        IllegalArgumentException exception = assertThrows( IllegalArgumentException.class,
                () -> historicoCompraService.findById( -1 ) );

        assertEquals( "ID DO HISTORICO INVALIDO", exception.getMessage() );
        verify( historicoCompraRepository, never() ).findById( anyInt() );
    }

    @Test
    @DisplayName("delete - Deve lançar exceção se ID for negativo")
    void cenario09() {
        IllegalArgumentException exception = assertThrows( IllegalArgumentException.class,
                () -> historicoCompraService.delete( -1 ) );

        assertEquals( "ID DO HISTORICO INVALIDO", exception.getMessage() );
        verify( historicoCompraRepository, never() ).deleteById( anyInt() );
    }
}