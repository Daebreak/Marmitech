package com.marmitech.Marmitech.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
//    @Column(name = "pedido_id_teste")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedido_id;

    private int cliente_id;
    private LocalDateTime data_pedido;
    private Double valor_total;
    private String status;
    private String endereco_entrega;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<HistoricoCompra> historicos;
}
