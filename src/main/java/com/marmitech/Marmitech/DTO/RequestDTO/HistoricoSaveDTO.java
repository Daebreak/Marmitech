package com.marmitech.Marmitech.DTO.RequestDTO;

public record HistoricoSaveDTO(
        Long usuarioId,
        String descricao,
        String dataEvento,
        String tipoEvento
) {
}