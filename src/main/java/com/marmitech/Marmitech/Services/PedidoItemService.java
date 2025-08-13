package com.marmitech.Marmitech.Service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Repository.PedidoItemRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoItemService {

    private Boolean isNumeroValido(int novoNum){
        return novoNum > 0;
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
        
        atualizadoPedidoItem.setPedidoId(validator(atualizadoPedidoItem.getPedidoId(), antigPedidoItem.getPedidoId(), this::isNumeroValido));
        atualizadoPedidoItem.setProdutoId(validator(atualizadoPedidoItem.getProdutoId(), antigPedidoItem.getProdutoId(), this::isNumeroValido));
        atualizadoPedidoItem.setPrecoUnitarioPedido(validator(atualizadoPedidoItem.getPrecoUnitarioPedido(), antigPedidoItem.getPrecoUnitarioPedido(), this::isNumeroValido));
        atualizadoPedidoItem.setQuantidade(validator(atualizadoPedidoItem.getQuantidade(), antigPedidoItem.getQuantidade(), this::isNumeroValido));
        atualizadoPedidoItem.setSubtotal(validator(atualizadoPedidoItem.getSubtotal(), antigPedidoItem.getSubtotal(), this::isNumeroValido));
        atualizadoPedidoItem.setId(pedidoItemId);
        
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

    public List<PedidoItem> findAll(){
        return pedidoItemRepository.findAll();
    }
}
