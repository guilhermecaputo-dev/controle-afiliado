package com.falcon.affiliatetracker.dto.response;

import com.falcon.affiliatetracker.models.enums.Plataforma;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaResponseDTO(
        Long id,
        BigDecimal valorComissao,
        String produtoNome,
        Long produtoId,
        LocalDate dataVenda,
        BigDecimal valorProduto,
        BigDecimal percentualComissao,
        Plataforma origem
) {
}
