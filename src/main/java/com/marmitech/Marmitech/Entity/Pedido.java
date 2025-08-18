package com.marmitech.Marmitech.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    //    @Column(name = "pedido_id_teste")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data_pedido;
    private BigDecimal valor_total;
    private String status;
    private String endereco_entrega;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
private Cliente cliente; // quem comprou


    @ManyToOne
    @JoinColumn(name = "usuario_id") //quem registrou o pedido
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoItem> pedidoItems = new HashSet<>();

    public void addItem(PedidoItem item) {
        item.setPedido(this);
        this.pedidoItems.add(item);
    }

}
