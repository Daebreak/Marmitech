package com.marmitech.Marmitech.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @NotBlank(message = "data_evento não pode ser null ou vazio")
    private String data_evento;

    @NotNull
    @NotBlank(message = "tipo_evento não pode ser null ou vazio")
    private String tipo_evento;

    @NotNull
    @NotBlank(message = "descricao não pode ser null ou vazio")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
