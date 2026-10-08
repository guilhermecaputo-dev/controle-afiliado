package com.falcon.affiliatetracker.dto.response;

import com.falcon.affiliatetracker.models.enums.Plataforma;

import java.math.BigDecimal;

public record VendasPorPlataformaDTO(
        Plataforma plataforma,
        Long quantidadeVendas,
        BigDecimal comissaoTotal
) {
}
