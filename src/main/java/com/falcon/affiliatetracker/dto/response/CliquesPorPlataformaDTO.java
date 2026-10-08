package com.falcon.affiliatetracker.dto.response;

import com.falcon.affiliatetracker.models.enums.Plataforma;

public record CliquesPorPlataformaDTO(
        Plataforma plataforma,
        Long quantidade
) {
}
