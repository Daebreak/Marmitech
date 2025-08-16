package com.marmitech.Marmitech.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedido_id;

    private int cliente_id;

    private LocalDateTime data_pedido;

    @NotNull
    @NotBlank(message = "valor_total não pode ser null ou vazio")
    private Double valor_total;

    @NotNull
    @NotBlank(message = "status não pode ser null ou vazio")
    private String status;

    @NotNull
    @NotBlank(message = "endereco_entrega não pode ser null ou vazio")
    private String endereco_entrega;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuarios;
}
