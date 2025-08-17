package com.marmitech.Marmitech.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedido_id;

    //private int cliente_id;
    private LocalDateTime data_pedido;
    private Double valor_total;
    private String status;
    private String endereco_entrega;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
private Cliente cliente; // quem comprou


    @ManyToOne
    @JoinColumn(name = "usuario_id") //quem registrou o pedido

    private Usuario usuario;
}
