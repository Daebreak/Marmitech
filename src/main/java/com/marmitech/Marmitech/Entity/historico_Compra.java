package com.marmitech.Marmitech.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class historico_Compra {
    @Id
    int historico_id;
    int pedido_id;
    int Cliente_id;
    String data_Mudanca;
    String tipo_Mudanca;
    String detalhes;
}
