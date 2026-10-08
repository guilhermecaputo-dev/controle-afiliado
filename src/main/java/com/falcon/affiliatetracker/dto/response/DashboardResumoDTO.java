package com.falcon.affiliatetracker.dto.response;

import java.math.BigDecimal;

public record DashboardResumoDTO(
        Long totalProdutos,
        Long totalConteudos,
        Long totalCliques,
        Long totalVendas,
        BigDecimal faturamento,
        BigDecimal comissao,
        BigDecimal taxaConversao
) {
}
