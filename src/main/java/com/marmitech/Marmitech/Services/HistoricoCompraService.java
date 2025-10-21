package com.marmitech.Marmitech.Services;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marmitech.Marmitech.Entity.HistoricoCompra;
import com.marmitech.Marmitech.Repository.HistoricoCompraRepository;

import jakarta.transaction.Transactional;

@Service
public class HistoricoCompraService {

    private Boolean isValidoString(String novaString) {
        return novaString != null && !novaString.isBlank();
    }

    private Boolean isValidoNum(int novoNum) {
        return novoNum > 0;
    }

    private <T> T validador(T novo, T antigo, Predicate<T> validar) {
        return validar.test( novo ) ? novo : antigo;
    }

    @Autowired
    private HistoricoCompraRepository historicoCompraRepository;

    @Transactional
    public HistoricoCompra save(HistoricoCompra novoHistoricoCompra) {
        return historicoCompraRepository.save( novoHistoricoCompra );
    }

    @Transactional
    public HistoricoCompra update(HistoricoCompra novoHistoricoCompra, int historicoCompraId) {
        HistoricoCompra antigoHistoricoCompra = historicoCompraRepository.findById( historicoCompraId )
                .orElseThrow( () -> new RuntimeException( "Historico com ID " + historicoCompraId + " não encontrado para atualização" ) );

        antigoHistoricoCompra.setTipoEvento( validador( novoHistoricoCompra.getTipoEvento(), antigoHistoricoCompra.getTipoEvento(), this::isValidoString ) );
        antigoHistoricoCompra.setDataEvento( validador( novoHistoricoCompra.getDataEvento(), antigoHistoricoCompra.getDataEvento(), this::isValidoString ) );
        antigoHistoricoCompra.setDescricao( validador( novoHistoricoCompra.getDescricao(), antigoHistoricoCompra.getDescricao(), this::isValidoString ) );

        return historicoCompraRepository.save( antigoHistoricoCompra );
    }

    @Transactional
    public String delete(int historicoCompraId) {
        if (historicoCompraId <= 0) {
            throw new IllegalArgumentException( "ID DO HISTORICO INVALIDO" );
        }
        historicoCompraRepository.deleteById( historicoCompraId );
        return "Historico deletado com sucesso!";
    }

    public HistoricoCompra findById(int historicoCompraId) {
        if (historicoCompraId <= 0) {
            throw new IllegalArgumentException( "ID DO HISTORICO INVALIDO" );
        }
        return historicoCompraRepository.findById( historicoCompraId )
                .orElseThrow( () -> new RuntimeException( "Historico com ID " + historicoCompraId + " não encontrado" ) );
    }

    public List<HistoricoCompra> findAll() {
        return historicoCompraRepository.findAll();
    }

    public List<HistoricoCompra> findByDataEvento(String dataEvento) {
        return historicoCompraRepository.findByDataEvento( dataEvento );
    }

}
