package com.marmitech.Marmitech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HistoricoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int pedidoId;
    private int clienteId;
    private String data_evento;
    private String tipo_evento;
    private String descricao;

    public HistoricoCompra(int pedidoId, int clienteId, String data_evento, String tipo_evento, String descricao) {
        if(pedidoId <= 0){
            throw new IllegalArgumentException("pedidoId não pode ser menor que 1.");
        }
        if(clienteId <= 0){
            throw new IllegalArgumentException("clienteId não pode ser menor que 1.");
        }
        if(data_evento == null || data_evento.isBlank()){
            throw new IllegalArgumentException("data_evento não pode ser null ou estar em branco.");
        }
        if(tipo_evento == null || tipo_evento.isBlank()){
            throw new IllegalArgumentException("tipo_evento não pode ser null ou estar em branco.");
        }
        if(descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("pedidoId não pode ser null ou estar em branco.");
        }

        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.data_evento = data_evento;
        this.tipo_evento = tipo_evento;
        this.descricao = descricao;
    }
}
