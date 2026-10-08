package com.falcon.affiliatetracker.dto.response;

import java.math.BigDecimal;

public record VendasPorProdutoDTO(
        String nome,
        Long quantidadeVendas,
        BigDecimal comissaoTotal
) {
}
