package com.marmitech.Marmitech.DTO.ResponseDTO;

import java.time.LocalDateTime;

public record HistoricoResponseDTO(
        Long id,
        String tipoEvento,
        LocalDateTime dataEvento,
        PedidoResponseDTO pedido
) {
}