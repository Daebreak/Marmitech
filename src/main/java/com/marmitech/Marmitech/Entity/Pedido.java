package com.marmitech.Marmitech.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private int cliente_id;
    //private int usuario_id;
    private String data_pedido;
    private BigDecimal valor_total;
    private String status;

    @NotNull
    @NotBlank(message = "endereco_entrega não pode ser null ou vazio")
    private String endereco_entrega;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoItem> pedidoItems = new HashSet<>();

    public void addItem(PedidoItem item) {
        item.setPedido(this);
        this.pedidoItems.add(item);
    }


    //Pedido que sera atrelado ao usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("pedidos")
    private Usuario usuario;

    //Pedido que sera atrelado ao historico
    @OneToMany(mappedBy = "pedido")
    private List<HistoricoCompra> historicos;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<HistoricoCompra> historicos;
}
