package com.marmitech.Marmitech.Services;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marmitech.Marmitech.DTO.ResponseDTO.PedidoItemResponseDTO;
import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Mapper.ResponseMapper.ProdutoItemResponseMapper;
import com.marmitech.Marmitech.Repository.PedidoItemRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoItemService {

    private Boolean isNumeroValido(int novoNum){
        return novoNum > 0;
    }

    private Boolean isPrecoValido(BigDecimal novoNum){
        return novoNum.compareTo(BigDecimal.ZERO) > 0;
    }

    private <T> T validator(T novo, T antigo, Predicate<T> validar){
        return validar.test(novo) ? novo : antigo;
    }
    
    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Transactional
    public PedidoItem save(PedidoItem novoPedidoItem){
        return pedidoItemRepository.save(novoPedidoItem);
    }

    @Transactional
    public PedidoItem update(PedidoItem atualizadoPedidoItem, int pedidoItemId){
        
        PedidoItem antigPedidoItem = pedidoItemRepository.findById(pedidoItemId).get();
        
        atualizadoPedidoItem.setPrecoUnitarioPedido(validator(atualizadoPedidoItem.getPrecoUnitarioPedido(), antigPedidoItem.getPrecoUnitarioPedido(), this::isPrecoValido));
        atualizadoPedidoItem.setQuantidade(validator(atualizadoPedidoItem.getQuantidade(), antigPedidoItem.getQuantidade(), this::isNumeroValido));
        atualizadoPedidoItem.setSubtotal(validator(atualizadoPedidoItem.getSubtotal(), antigPedidoItem.getSubtotal(), this::isPrecoValido));
        
        return pedidoItemRepository.save(atualizadoPedidoItem);
    }

    @Transactional
    public String delete(int pedidoItemId){
        pedidoItemRepository.deleteById(pedidoItemId);

        return "Linha deleteda da tabela.";
    }

    public PedidoItem findById(int pedidoId){
        return pedidoItemRepository.findById(pedidoId).get();
    }

    public List<PedidoItemResponseDTO> findAll(){
        return pedidoItemRepository.findAll().stream().map(ProdutoItemResponseMapper::toDto).toList();
    }
}
