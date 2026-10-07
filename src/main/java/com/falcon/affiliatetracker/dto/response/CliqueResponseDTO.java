package com.falcon.affiliatetracker.dto.response;

import com.falcon.affiliatetracker.models.enums.Plataforma;

import java.time.LocalDateTime;

public record CliqueResponseDTO(
        Long id,
        LocalDateTime dataHora,
        Plataforma origem,
        Long linkAfiliadoId,
        String linkCodigo
) {
}
