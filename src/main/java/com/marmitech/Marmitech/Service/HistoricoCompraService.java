package com.marmitech.Marmitech.Service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marmitech.Marmitech.Entity.HistoricoCompra;
import com.marmitech.Marmitech.Repository.HistoricoCompraRepository;

import jakarta.transaction.Transactional;

@Service
public class HistoricoCompraService {

    private Boolean isValidoString(String novaString){
        return novaString != null && !novaString.isBlank();
    }

    private Boolean isValidoNum(int novoNum){
        return novoNum > 0;
    }

    private <T> T validador(T novo, T antigo, Predicate<T> validar){
        return validar.test(novo) ? novo : antigo;
    }

    @Autowired
    private HistoricoCompraRepository historicoCompraRepository;

    @Transactional
    public HistoricoCompra save(HistoricoCompra novoHistoricoCompra){
        return historicoCompraRepository.save(novoHistoricoCompra);
    }

    @Transactional
    public HistoricoCompra update(HistoricoCompra novoHistoricoCompra, int historicoCompraId){
        HistoricoCompra antigoHistoricoCompra = historicoCompraRepository.findById(historicoCompraId).get();

        novoHistoricoCompra.setClienteId(validador(novoHistoricoCompra.getClienteId(), antigoHistoricoCompra.getClienteId(), this::isValidoNum));
        novoHistoricoCompra.setPedidoId(validador(novoHistoricoCompra.getPedidoId(), antigoHistoricoCompra.getPedidoId(), this::isValidoNum));
        novoHistoricoCompra.setTipo_evento(validador(novoHistoricoCompra.getTipo_evento(), antigoHistoricoCompra.getTipo_evento(), this::isValidoString));
        novoHistoricoCompra.setData_evento(validador(novoHistoricoCompra.getData_evento(), antigoHistoricoCompra.getData_evento(), this::isValidoString));
        novoHistoricoCompra.setDescricao(validador(novoHistoricoCompra.getDescricao(), antigoHistoricoCompra.getDescricao(), this::isValidoString));
        novoHistoricoCompra.setHistoricoId(historicoCompraId);

        return novoHistoricoCompra;
    }

    @Transactional
    public String delete(int historicoCompraId){
        historicoCompraRepository.deleteById(historicoCompraId);

        return "Historico deletado com sucesso!";
    }

    public HistoricoCompra findById(int historicoCompraId){
        return historicoCompraRepository.findById(historicoCompraId).get();
    }

    public List<HistoricoCompra> findAll(){
        return historicoCompraRepository.findAll();
    }

}
